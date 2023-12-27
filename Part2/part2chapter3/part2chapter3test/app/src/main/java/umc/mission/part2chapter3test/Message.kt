package umc.mission.part2chapter3test

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("message")
    val message: String
)
