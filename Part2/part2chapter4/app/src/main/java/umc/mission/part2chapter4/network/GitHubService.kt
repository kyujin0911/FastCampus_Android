package umc.mission.part2chapter4.network

import retrofit2.Call
import retrofit2.http.*
import umc.mission.part2chapter4.model.Repo
import umc.mission.part2chapter4.model.UserDto

interface GitHubService {
    @Headers("Authorization: Bearer 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")
    @GET("/users/{username}/repos")
    fun listRepos(@Path("username") username: String): Call<List<Repo>>

    @Headers("Authorization: Bearer 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")
    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<UserDto>
}