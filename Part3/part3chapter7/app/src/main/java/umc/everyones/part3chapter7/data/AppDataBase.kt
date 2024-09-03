package umc.everyones.part3chapter7.data

import androidx.room.Database
import androidx.room.RoomDatabase
import umc.everyones.part3chapter7.data.dao.ContentDao
import umc.everyones.part3chapter7.model.ContentEntity

@Database(entities = [ContentEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun contentDao(): ContentDao
}