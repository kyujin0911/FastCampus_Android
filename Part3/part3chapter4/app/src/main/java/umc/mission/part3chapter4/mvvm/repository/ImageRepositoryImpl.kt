package umc.mission.part3chapter4.mvvm.repository

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import umc.mission.part3chapter4.RetrofitManager
import umc.mission.part3chapter4.mvvm.model.Image

class ImageRepositoryImpl : ImageRepository {

    override fun getRandomImage() = RetrofitManager.imageService.getRandomImageRx()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap { item ->
            Single.just(Image(item.urls.regular, item.color))
        }
}