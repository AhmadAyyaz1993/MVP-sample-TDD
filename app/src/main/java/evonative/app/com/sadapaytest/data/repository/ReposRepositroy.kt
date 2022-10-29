package evonative.app.com.sadapaytest.data.repository

import evonative.app.com.sadapaytest.R
import evonative.app.com.sadapaytest.api.ReposApi
import evonative.app.com.sadapaytest.data.Resource
import evonative.app.com.sadapaytest.data.model.ResponseParent
import javax.inject.Inject

class ReposRepositroy @Inject constructor(private val api: ReposApi):IReposRepository {
    override suspend fun fetchRepos(): Resource<ResponseParent> {
        val response = api.fetchRepos()

        when (response.code()) {
            404 -> {
                return Resource.error("Not Found",response.code() ,null)
            }
            in 500..600 -> {
                return Resource.error("Internal Server Error", response.code(),null)
            }
            else -> return if (response.body() == null) {
                Resource.error("Server Error",123,null)
            } else {
                Resource.success(response.body())
            }
        }
    }
}