package umc.everyones.part3chapter7

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import umc.everyones.part3chapter7.databinding.ActivityMainBinding
import umc.everyones.part3chapter7.model.ContentEntity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()
    private val adapter by lazy { ListAdapter(Handler()) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            view = this@MainActivity
            rv.adapter = adapter
            val decoration = DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL)
            rv.addItemDecoration(decoration)
        }

        lifecycleScope.launch {
            viewModel.contentList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    binding.rv.isVisible = it.isNotEmpty()
                    binding.emptyTv.isVisible = it.isEmpty()
                    adapter.submitList(it)
                }
        }
    }

    fun onClickAdd(){
        InputActivity.start(this)
    }

    inner class Handler{
        fun onClickItem(item: ContentEntity){
            InputActivity.start(this@MainActivity, item)
        }

        fun onCheckedItem(item: ContentEntity, checked: Boolean){
            viewModel.updateItem(item.copy(isDone = checked))
        }

        fun onLongClickItem(item: ContentEntity): Boolean{
            viewModel.deleteItem(item)
            Toast.makeText(this@MainActivity, "삭제 완료", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}