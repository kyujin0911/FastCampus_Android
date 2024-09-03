package umc.everyones.part3chapter7.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import umc.everyones.part3chapter7.model.ContentEntity

@Dao
interface ContentDao {

    @Query("SELECT * FROM Content")
    fun selectAll(): Flow<List<ContentEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ContentEntity)

}