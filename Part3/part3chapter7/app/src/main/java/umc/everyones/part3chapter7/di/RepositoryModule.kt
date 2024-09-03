package umc.everyones.part3chapter7.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import umc.everyones.part3chapter7.data.dao.ContentDao
import umc.everyones.part3chapter7.repository.ContentRepository
import umc.everyones.part3chapter7.repository.ContentRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providesContentRepository(contentDao: ContentDao): ContentRepository = ContentRepositoryImpl(contentDao)
}