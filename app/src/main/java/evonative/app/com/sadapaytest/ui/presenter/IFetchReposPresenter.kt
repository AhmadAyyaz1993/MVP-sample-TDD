package evonative.app.com.sadapaytest.ui.presenter

interface IFetchReposPresenter {
    /**
     * The logic that is executed when loading data from the server is performed in this method.
     * */
    fun loadRepos()

    fun cancel()
}