package evonative.app.com.sadapaytest.ui

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import evonative.app.com.sadapaytest.data.model.Repo
import evonative.app.com.sadapaytest.databinding.ItemRepoBinding

class ReposAdapter(context: Context) : RecyclerView.Adapter<ReposAdapter.PostVH>() {

    private val inflate by lazy { LayoutInflater.from(context) }

    private var posts = mutableListOf<Repo>()
    private lateinit var binding: ItemRepoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        binding = ItemRepoBinding.inflate(inflate, parent, false)
        return PostVH(binding)

    }

    override fun onBindViewHolder(holder: PostVH, position: Int) = holder.bind()

    override fun getItemCount(): Int = posts.size

    fun submitRepo(it: List<Repo>) {
        posts.clear()
        posts.addAll(it)
        notifyItemRangeRemoved(0, it.size)
    }

    inner class PostVH(private var binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.apply {
                this.setOnClickListener {
                    if (binding.collapsable.isVisible){
                        binding.collapsable.visibility = View.GONE
                    }else{
                        binding.collapsable.visibility = View.VISIBLE
                    }
                }
            }
        }

        fun bind() = bindItem {
            binding.ownerName.text = posts[adapterPosition].owner.login
            binding.repoName.text = posts[adapterPosition].name
            binding.description.text = posts[adapterPosition].description
            Glide.with(context).load(posts[adapterPosition].owner.avatar_url).into(binding.profileImage)
            binding.tvLanguage.text = posts[adapterPosition].language
            binding.tvStars.text = posts[adapterPosition].stargazersCount.toString()
        }
    }
    fun RecyclerView.ViewHolder.bindItem(block: View.() -> Unit) = block(itemView)
}