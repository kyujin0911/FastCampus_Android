package umc.mission.part2chapter4.network

import retrofit2.Call
import retrofit2.http.*
import umc.mission.part2chapter4.model.Repo
import umc.mission.part2chapter4.model.UserDto

interface GitHubService {
    @Headers("Authorization: Bearer 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")
    @GET("/users/{username}/repos")
    fun listRepos(@Path("username") username: String, @Query("page") page: Int): Call<List<Repo>>

    @Headers("Authorization: Bearer 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")
    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<UserDto>
}