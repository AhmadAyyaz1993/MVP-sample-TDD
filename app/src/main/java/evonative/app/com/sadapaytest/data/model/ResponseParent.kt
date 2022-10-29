package evonative.app.com.sadapaytest.data.model

import com.google.gson.annotations.SerializedName

data class ResponseParent(
    @SerializedName("total_count")
    val total_count: Int,
    @SerializedName("incomplete_results")
    val incomplete_results: Boolean,
    @SerializedName("message")
    val message: String?,
    @SerializedName("items")
    val items: List<Repo>

)
