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
import umc.mission.part2chapter4.adapter.RepoRVA
import umc.mission.part2chapter4.databinding.ActivityRepoBinding
import umc.mission.part2chapter4.model.Repo
import umc.mission.part2chapter4.network.GitHubService

class RepoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoBinding
    private lateinit var repoRVA: RepoRVA

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username") ?: return

        binding.usernameTV.text = username

        repoRVA = RepoRVA()

        binding.repoRV.apply {
            layoutManager = LinearLayoutManager(this@RepoActivity)
            adapter = repoRVA
        }

        listRepo(username)

    }

    private fun listRepo(username: String){
        val githubService = retrofit.create(GitHubService::class.java)
        githubService.listRepos(username).enqueue(object: Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                Log.e("MainActivity", "List Repo: ${response.body().toString()}")

                repoRVA.submitList(response.body())
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

            }

        })
    }
}