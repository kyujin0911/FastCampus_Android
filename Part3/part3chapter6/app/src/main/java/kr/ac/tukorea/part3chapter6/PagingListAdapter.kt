package kr.ac.tukorea.part3chapter6

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import kr.ac.tukorea.part3chapter6.model.ListItem
import kr.ac.tukorea.part3chapter6.model.ViewHolderGenerator
import kr.ac.tukorea.part3chapter6.viewholder.BindingViewHolder

class PagingListAdapter:
    PagingDataAdapter<ListItem, BindingViewHolder<*>>(DiffCallback()){

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return item?.viewType?.ordinal ?: -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<*> {
        return ViewHolderGenerator.get(parent, viewType)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<*>, position: Int) {
        val item = getItem(position)
        if(item != null){
            holder.bind(item)
        }
    }
}