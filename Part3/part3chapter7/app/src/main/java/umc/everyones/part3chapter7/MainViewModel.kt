package umc.everyones.part3chapter7

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import umc.everyones.part3chapter7.model.ContentEntity
import umc.everyones.part3chapter7.repository.ContentRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ContentRepository
): ViewModel(){

    val contentList = repository.loadList()
        .stateIn(
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(5000),
            scope = viewModelScope
        )

    fun updateItem(item: ContentEntity){
        viewModelScope.launch{
            repository.modify(item)
        }
    }

    fun deleteItem(item: ContentEntity){
        viewModelScope.launch {
            repository.delete(item)
        }
    }
}