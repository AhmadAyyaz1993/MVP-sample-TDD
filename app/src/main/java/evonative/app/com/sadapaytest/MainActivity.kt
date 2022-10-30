package evonative.app.com.sadapaytest

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import evonative.app.com.sadapaytest.data.model.Repo
import evonative.app.com.sadapaytest.databinding.ActivityMainBinding
import evonative.app.com.sadapaytest.ui.ReposAdapter
import evonative.app.com.sadapaytest.ui.presenter.FetchReposPresenter
import evonative.app.com.sadapaytest.ui.presenter.IView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider


@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity(), IView {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ReposAdapter

    @InjectPresenter
    lateinit var reposPresenter: FetchReposPresenter

    @Inject
    lateinit var presenterProvider: Provider<FetchReposPresenter>

    @ProvidePresenter
    fun providePresenter(): FetchReposPresenter {
        return presenterProvider.get()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.shimmerLayout.startShimmer()
        adapter = ReposAdapter(this)

        initRv()

        binding.actionMenu.setOnClickListener {
            val popup = PopupMenu(this@MainActivity, binding.actionMenu)

            val nightModeFlags: Int = MainActivity@this.getResources().getConfiguration().uiMode and
                    Configuration.UI_MODE_NIGHT_MASK

            popup.menu.clear()
            if(nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                popup.menu.add("Disable Dard Mode")
            }else{
                popup.menu.add("Enable Dard Mode")
            }

            //registering popup with OnMenuItemClickListener

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem): Boolean {
                    if(nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }else{
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    return true
                }
            })

            popup.show() //showing popup menu

        }
    }
    private fun initRv() {
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter
    }
    override fun showRefresh() {
        binding.shimmerLayout.startShimmer()
    }

    override fun hideRefresh() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE;

    }

    override fun addRepos(repos: List<Repo>) {
        binding.somethignWentWrong.root.visibility= View.GONE
        binding.rv.visibility = View.VISIBLE
        adapter.submitRepo(repos)
    }

    override fun onFail() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE;
        binding.somethignWentWrong.root.visibility= View.VISIBLE
        binding.rv.visibility = View.GONE
        Glide.with(MainActivity@this).load(R.raw.something_went_wrong).into(binding.somethignWentWrong.ivSomethignWentWrong)
        binding.somethignWentWrong.btnRetry.setOnClickListener {
            binding.shimmerLayout.startShimmer()
            binding.shimmerLayout.visibility = View.VISIBLE;
            binding.somethignWentWrong.root.visibility= View.GONE
            reposPresenter.loadRepos()
        }
    }

    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }
}