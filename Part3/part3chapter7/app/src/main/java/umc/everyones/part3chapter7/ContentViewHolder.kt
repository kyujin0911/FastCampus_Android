package umc.everyones.part3chapter7

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import umc.everyones.part3chapter7.databinding.ItemContentBinding
import umc.everyones.part3chapter7.model.ContentEntity

class ContentViewHolder(
    private val binding: ItemContentBinding,
    private val handler: MainActivity.Handler
) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: ContentEntity){
        binding.item = item
        binding.handler = handler
        binding.contentCb.paintFlags = if(item.isDone){
            Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            0
        }
    }
}