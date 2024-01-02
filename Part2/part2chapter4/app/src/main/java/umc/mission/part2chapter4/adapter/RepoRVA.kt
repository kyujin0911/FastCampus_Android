package umc.mission.part2chapter4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import umc.mission.part2chapter4.databinding.ItemRepoBinding
import umc.mission.part2chapter4.model.Repo

class RepoRVA(private val onClick: (Repo) -> Unit): ListAdapter<Repo, RepoRVA.RepoViewHolder>(diffUtil) {

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<Repo>(){
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class RepoViewHolder(private val binding: ItemRepoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(repo: Repo){
            binding.repoNameTV.text = repo.name
            binding.descriptionTV.text = repo.description
            binding.forkCountTV.text = repo.forkCount.toString()
            binding.starCountTV.text = repo.starCount.toString()
            binding.root.setOnClickListener {
                onClick(repo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}