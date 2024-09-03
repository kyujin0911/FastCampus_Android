package umc.everyones.part3chapter7.repository

import kotlinx.coroutines.flow.Flow
import umc.everyones.part3chapter7.data.dao.ContentDao
import umc.everyones.part3chapter7.model.ContentEntity
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDao: ContentDao
): ContentRepository {
    override suspend fun insert(item: ContentEntity) {
        contentDao.insert(item)
    }

    override fun loadList(): Flow<List<ContentEntity>> {
        return contentDao.selectAll()
    }

    override suspend fun modify(item: ContentEntity) {
        contentDao.insert(item)
    }

    override suspend fun delete(item: ContentEntity) {
        contentDao.delete(item)
    }
}