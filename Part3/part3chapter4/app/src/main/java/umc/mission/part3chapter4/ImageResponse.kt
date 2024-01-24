package umc.mission.part3chapter4

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("urls")
    val urls: UrlResponse,
    @SerializedName("color")
    val color: String,
)

data class UrlResponse(
    @SerializedName("raw") val raw : String,
    @SerializedName("full") val full : String,
    @SerializedName("regular") val regular : String,
    @SerializedName("small") val small : String,
    @SerializedName("thumb") val thumb : String,
)
