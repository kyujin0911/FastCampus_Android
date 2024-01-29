package kr.ac.tukorea.part3chapter6.remote.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.ac.tukorea.part3chapter6.model.ListItem

interface MainRepository {

    fun loadList(): Flow<PagingData<ListItem>>
}