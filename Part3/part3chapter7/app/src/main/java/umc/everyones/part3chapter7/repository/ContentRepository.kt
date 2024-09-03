package umc.everyones.part3chapter7.repository

import kotlinx.coroutines.flow.Flow
import umc.everyones.part3chapter7.model.ContentEntity

interface ContentRepository {
    suspend fun insert(item: ContentEntity)

    fun loadList(): Flow<List<ContentEntity>>
}