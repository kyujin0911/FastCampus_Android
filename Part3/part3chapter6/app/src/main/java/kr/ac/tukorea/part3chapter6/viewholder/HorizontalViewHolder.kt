package kr.ac.tukorea.part3chapter6.viewholder

import kr.ac.tukorea.part3chapter6.ListAdapter
import kr.ac.tukorea.part3chapter6.databinding.ItemHorizontalBinding
import kr.ac.tukorea.part3chapter6.model.Horizontal
import kr.ac.tukorea.part3chapter6.model.ListItem

class HorizontalViewHolder (
    private val binding: ItemHorizontalBinding
) : BindingViewHolder<ItemHorizontalBinding>(binding){
    private val adapter = ListAdapter()

    init {
        binding.listRv.adapter = adapter
    }

    override fun bind(item: ListItem) {
        super.bind(item)
        item as Horizontal
        binding.titleTv.text = item.title
        adapter.submitList(item.items)
    }
}