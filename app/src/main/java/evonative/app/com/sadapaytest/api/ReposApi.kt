package evonative.app.com.sadapaytest.api

import evonative.app.com.sadapaytest.data.model.Repo
import evonative.app.com.sadapaytest.data.model.ResponseParent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.github.com/"

interface ReposApi {
    /**
     * 1. Fetch the repos from endpoint
     * */
    @GET("search/repositories")
    suspend fun fetchRepos(@Query("repositories") apiKey: String = "language=+sort:stars"): Response<ResponseParent>
}