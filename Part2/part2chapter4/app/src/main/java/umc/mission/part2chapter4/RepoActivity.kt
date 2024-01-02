package umc.mission.part2chapter4

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import umc.mission.part2chapter4.APIClient.retrofit
import umc.mission.part2chapter4.adapter.RepoRVA
import umc.mission.part2chapter4.databinding.ActivityRepoBinding
import umc.mission.part2chapter4.model.Repo
import umc.mission.part2chapter4.network.GitHubService

class RepoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoBinding
    private lateinit var repoRVA: RepoRVA
    private var page = 0
    private var hasMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username") ?: return

        binding.usernameTV.text = username

        repoRVA = RepoRVA{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.htmlUrl))
            startActivity(intent)
        }
        val linearLayoutManager = LinearLayoutManager(this@RepoActivity)

        binding.repoRV.apply {
            layoutManager = linearLayoutManager
            adapter = repoRVA
        }

        binding.repoRV.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalCount = linearLayoutManager.itemCount
                val lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()

                // position은 0부터, count는 1부터 시작하기 때문에 totalCount - 1
                if (lastVisiblePosition >= totalCount - 1 && hasMore){
                    listRepo(username, ++page)
                }
            }
        })

        listRepo(username, 0) // 1번째 호출될 떄는 0번째 page부터

    }

    private fun listRepo(username: String, page: Int){
        val githubService = APIClient.retrofit.create(GitHubService::class.java)
        githubService.listRepos(username, page).enqueue(object: Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                Log.e("MainActivity", "List Repo: ${response.body().toString()}")
                hasMore = response.body()?.count() == 30
                repoRVA.submitList(repoRVA.currentList + response.body().orEmpty())
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

            }

        })
    }
}