package umc.mission.part2chapter4.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import umc.mission.part2chapter4.model.Repo

interface GitHubService {
    @GET("/users/{username}/repos")
    fun listRepos(@Path("username") username: String): Call<List<Repo>>
}