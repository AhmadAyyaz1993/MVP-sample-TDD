package evonative.app.com.sadapaytest.data.model

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val full_name: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("owner")
    val owner: RepoOwner,
    @SerializedName("description")
    val description: String
)
