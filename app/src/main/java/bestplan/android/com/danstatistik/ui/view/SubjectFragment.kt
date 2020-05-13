package bestplan.android.com.danstatistik.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import bestplan.android.com.danstatistik.R
import bestplan.android.com.danstatistik.data.model.Subject
import bestplan.android.com.danstatistik.di.Injectable
import bestplan.android.com.danstatistik.di.injectViewModel
import bestplan.android.com.danstatistik.ui.adapter.SubjectAdapter
import bestplan.android.com.danstatistik.ui.viewmodel.SubjectViewModel
import bestplan.android.com.danstatistik.utils.Status
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class SubjectFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var subjectViewModel: SubjectViewModel
    private val subjectList: MutableList<Subject> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        subjectViewModel = injectViewModel(viewModelFactory)
        return inflater.inflate(R.layout.fragment_subject, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter =
                SubjectAdapter(
                    subjectList
                )
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

    }

    override fun onResume() {
        super.onResume()

        activity?.title = getString(R.string.app_name)

        subjectViewModel.subjects.observe(viewLifecycleOwner, Observer { subjects ->
            when (subjects.status) {
                Status.SUCCESS -> {
                    subjectList.clear()
                    subjects.data?.toCollection(subjectList)
                    recyclerview.adapter?.notifyDataSetChanged()

                }
                Status.ERROR, Status.LOADING -> textStatus.text = subjects.message
            }
        })


    }
}
