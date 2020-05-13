package bestplan.android.com.danstatistik.api

import bestplan.android.com.danstatistik.utils.Resource
import retrofit2.Response
import timber.log.Timber

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.e(message)
        return Resource.error("Network call has failed for a following reason: $message")
    }

}

