package umc.everyones.part3chapter7

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import umc.everyones.part3chapter7.model.ContentEntity
import umc.everyones.part3chapter7.repository.ContentRepository
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
    private val repository: ContentRepository
): ViewModel() {
    private val _doneEvent = MutableLiveData<Unit>()
    val doneEvent: LiveData<Unit> get() = _doneEvent

    var content = MutableLiveData<String>()
    var memo = MutableLiveData<String?>()

    fun insertData(){
        content.value?.let {content ->
            viewModelScope.launch {
                repository.insert(
                    ContentEntity(content = content, memo = memo.value)
                )
                _doneEvent.postValue(Unit)
            }
        }
    }

}