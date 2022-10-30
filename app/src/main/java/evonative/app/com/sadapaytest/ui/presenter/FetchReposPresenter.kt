package evonative.app.com.sadapaytest.ui.presenter

import evonative.app.com.sadapaytest.App
import evonative.app.com.sadapaytest.data.Resource
import evonative.app.com.sadapaytest.data.repository.IReposRepository
import evonative.app.com.sadapaytest.utils
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

@InjectViewState
class FetchReposPresenter @Inject constructor(private var reposRepository: IReposRepository): IFetchReposPresenter,
    MvpPresenter<IView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (utils.internetConnectionAvailable(5000)) {
            loadRepos()
        } else
        {
            viewState.onFail()
        }
    }

    override fun loadRepos() {
        viewState.showRefresh()
        presenterScope.launch {
            try {
                reposRepository.fetchRepos().let {
                    if (it.status == Resource.Status.SUCCESS){
                        viewState.addRepos(it.data!!.items)
                    }else{
                        viewState.onFail()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.onFail()
            } finally {
                viewState.hideRefresh()
            }
        }
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }
}