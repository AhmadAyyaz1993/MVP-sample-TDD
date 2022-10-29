package evonative.app.com.sadapaytest.data

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message:String?,
    val code:Int?
    ){

    companion object{
        fun <T> success(data:T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null,200)
        }
        fun <T> error(msg:String,code:Int, data:T?): Resource<T> {
            return Resource(Status.ERROR, data, msg,code)
        }
    }
    enum class Status {
        SUCCESS,
        ERROR,
    }
}