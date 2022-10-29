package evonative.app.com.sadapaytest.data.repository


import evonative.app.com.sadapaytest.data.Resource
import evonative.app.com.sadapaytest.data.model.ResponseParent

interface IReposRepository {
    /**
     *The fetchRepos () method in our model section performs the task of loading data from the server.
     * */
    suspend fun fetchRepos(): Resource<ResponseParent>
}