package bestplan.android.com.danstatistik.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import bestplan.android.com.danstatistik.R
import bestplan.android.com.danstatistik.data.model.Subject
import bestplan.android.com.danstatistik.ui.view.SubjectFragmentDirections


class SubjectAdapter(private val list: List<Subject>) : RecyclerView.Adapter<SubjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SubjectViewHolder(
            inflater.inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) =
        holder.bind(list[position])
}


class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mSubjectView: TextView? = null

    init {
        mSubjectView = itemView.findViewById(R.id.name)
    }

    fun bind(subject: Subject) {
        mSubjectView?.text = subject.description
        itemView.setOnClickListener {
            val actionSubjectToTable = SubjectFragmentDirections.actionSubjectToTable(subject.id)
            Navigation.findNavController(it).navigate(actionSubjectToTable)

        }
    }

}