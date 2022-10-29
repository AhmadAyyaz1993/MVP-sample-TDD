package evonative.app.com.sadapaytest.data.model

import com.google.gson.annotations.SerializedName

data class RepoOwner(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatar_url: String
)
