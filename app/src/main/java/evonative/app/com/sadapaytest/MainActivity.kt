package evonative.app.com.sadapaytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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

        adapter = ReposAdapter(this)

        initRv()
    }
    private fun initRv() {
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter
    }
    override fun showRefresh() {

    }

    override fun hideRefresh() {

    }

    override fun addRepos(repos: List<Repo>) {
        adapter.submitRepo(repos)
    }

    override fun onFail() {
        TODO("Not yet implemented")
    }

    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }
}