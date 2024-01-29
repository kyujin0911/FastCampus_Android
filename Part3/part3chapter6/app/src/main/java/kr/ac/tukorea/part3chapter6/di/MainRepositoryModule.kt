package kr.ac.tukorea.part3chapter6.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kr.ac.tukorea.part3chapter6.remote.MainService
import kr.ac.tukorea.part3chapter6.remote.repository.MainRepository
import kr.ac.tukorea.part3chapter6.remote.repository.MainRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object MainRepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesMainRepository(
        mainService: MainService
    ) : MainRepository = MainRepositoryImpl(mainService)
}