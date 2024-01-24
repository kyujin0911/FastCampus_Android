package umc.mission.part3chapter4

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ImageService {
    @Headers("Authorization: Client-ID BU-jBJkV67_6O419_o8ioSUWxHVcef_5uXLHmSAYSNc")
    @GET("random")
    fun getRandomImage(): Call<ImageResponse>

    @Headers("Authorization: Client-ID BU-jBJkV67_6O419_o8ioSUWxHVcef_5uXLHmSAYSNc")
    @GET("random")
    fun getRandomImageRx(): Single<ImageResponse>
}