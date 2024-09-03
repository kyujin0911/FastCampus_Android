package umc.everyones.part3chapter7.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import umc.everyones.part3chapter7.data.AppDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun providesContentDao(appDataBase: AppDataBase) = appDataBase.contentDao()
}