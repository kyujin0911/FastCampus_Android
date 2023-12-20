package fastcampus.part1.chapter8

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fastcampus.part1.chapter8.databinding.ItemImageBinding
import fastcampus.part1.chapter8.databinding.ItemLoadMoreBinding

class ImageAdapter(private val itemClickListener: ItemClickListener) : ListAdapter<ImageItems, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<ImageItems>() {
        override fun areItemsTheSame(oldItem: ImageItems, newItem: ImageItems): Boolean {
            return oldItem === newItem // 같은 값을 참조하고 있는지에 대해 참조의 동등성을 표현하려면 "===" 사용
        }

        override fun areContentsTheSame(oldItem: ImageItems, newItem: ImageItems): Boolean {
            return oldItem == newItem // 코틀린은 자바와 달리 "=="는 equal에 대한 표현
        }

    }
) {

    override fun getItemCount(): Int {
        val originSize = currentList.size
        return if (originSize == 0) 0 else originSize.inc()
    }

    override fun getItemViewType(position: Int): Int { //맨 마지막이면 무조건 아이템 footer 삽입
        return if (itemCount.dec() == position) ITEM_LOAD_MORE else ITEM_IMAGE
        //recyclerView의 position은 0부터 시작
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return when (viewType) {
            ITEM_IMAGE -> {
                val binding = ItemImageBinding.inflate(inflater, parent, false)
                ImageViewHolder(binding)
            }

            else -> {
                val binding = ItemLoadMoreBinding.inflate(inflater, parent, false)
                LoadMoreViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ImageViewHolder -> {
                holder.bind(currentList[position] as ImageItems.Image)
            }
            is LoadMoreViewHolder -> {
                holder.bind(itemClickListener)
            }
        }
    }

    interface ItemClickListener {
        fun onLoadMoreClick()
    }

    companion object {
        const val ITEM_IMAGE = 0
        const val ITEM_LOAD_MORE = 1
    }
}

sealed class ImageItems {
    data class Image(
        val uri: Uri,
    ) : ImageItems()

    object LoadMore : ImageItems()
}

class ImageViewHolder(private val binding: ItemImageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ImageItems.Image) {
        binding.previewImageView.setImageURI(item.uri)
    }

}

class LoadMoreViewHolder(private val binding: ItemLoadMoreBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(itemClickListener: ImageAdapter.ItemClickListener) {
        itemView.setOnClickListener { itemClickListener?.onLoadMoreClick() }
    } //어떻게 들어가는지 복습 필요
}

