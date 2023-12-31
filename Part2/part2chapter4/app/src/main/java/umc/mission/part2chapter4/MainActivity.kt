package umc.mission.part2chapter4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import umc.mission.part2chapter4.adapter.UserRVA
import umc.mission.part2chapter4.databinding.ActivityMainBinding
import umc.mission.part2chapter4.model.Repo
import umc.mission.part2chapter4.model.UserDto
import umc.mission.part2chapter4.network.GitHubService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val githubService = retrofit.create(GitHubService::class.java)
        githubService.listRepos("kyujin0911").enqueue(object: Callback<List<Repo>>{
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                Log.e("MainActivity", "List Repo: ${response.body().toString()}")
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

            }

        })

        val userRVA = UserRVA()
        binding.userRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userRVA
        }

        githubService.searchUsers("kyujin").enqueue(object: Callback<UserDto>{
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                Log.e("MainActivity", "search User: ${response.body().toString()}")

                userRVA.submitList(response.body()?.items)
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}