package bestplan.android.com.danstatistik.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bestplan.android.com.danstatistik.R
import bestplan.android.com.danstatistik.data.model.Table



class TableAdapter(private val list: List<Table>) : RecyclerView.Adapter<TableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TableViewHolder(
            inflater.inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) =
        holder.bind(list[position])
}


class TableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mTextView: TextView? = null

    init {
        mTextView = itemView.findViewById(R.id.name)
    }

    fun bind(table: Table) {
        mTextView?.text = table.text
    }

}