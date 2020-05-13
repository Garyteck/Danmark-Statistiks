package bestplan.android.com.danstatistik.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import bestplan.android.com.danstatistik.R
import bestplan.android.com.danstatistik.data.model.Table
import bestplan.android.com.danstatistik.di.Injectable
import bestplan.android.com.danstatistik.di.injectViewModel
import bestplan.android.com.danstatistik.ui.adapter.TableAdapter
import bestplan.android.com.danstatistik.ui.viewmodel.TableViewModel
import bestplan.android.com.danstatistik.utils.Status
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber
import javax.inject.Inject

class TableFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tableViewModel: TableViewModel

    private val tableList: MutableList<Table> = mutableListOf()

    private val args: TableFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tableViewModel = injectViewModel(viewModelFactory)
        tableViewModel.id = args.subjectID
        Timber.e(tableViewModel.id)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TableAdapter(tableList)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }


    }

    override fun onResume() {
        super.onResume()
        tableViewModel.tables.observe(viewLifecycleOwner, Observer { table ->
            when (table.status) {
                Status.SUCCESS -> {
                    tableList.clear()
                    table.data?.toCollection(tableList)
                    recyclerview.adapter?.notifyDataSetChanged()

                }
                Status.ERROR, Status.LOADING -> textStatus.text = table.message
            }
        })

        tableViewModel.subject.observe(viewLifecycleOwner, Observer { subject ->
            activity?.title = subject.description
        })

    }
}
