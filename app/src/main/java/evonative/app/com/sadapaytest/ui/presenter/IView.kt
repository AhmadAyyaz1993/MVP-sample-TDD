package evonative.app.com.sadapaytest.ui.presenter

import evonative.app.com.sadapaytest.data.model.Repo
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface IView:MvpView {
    /**
     * Refresh starts and loads the data
     * */
    @AddToEndSingle
    fun showRefresh()

    /**
     * Refresh closes
     * */
    @AddToEndSingle
    fun hideRefresh()

    /**
     * The uploaded data is output to the UI.
     * */
    @AddToEndSingle
    fun addRepos(repos: List<Repo>)

    /**
     * issue errors to ui
     * */
    @OneExecution
    fun onFail()

    @AddToEndSingle
    fun showMessage(message: String)
}