package umc.mission.part3chapter4.mvvm.repository

import io.reactivex.Single
import io.reactivex.Observable
import umc.mission.part3chapter4.mvvm.model.Image

interface ImageRepository {

    fun getRandomImage(): Single<Image>
}