package umc.mission.part2chapter4.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    // Dto: data transfer object의 약자
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("items")
    val items: List<User>
    )
