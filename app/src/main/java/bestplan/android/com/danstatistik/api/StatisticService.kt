package bestplan.android.com.danstatistik.api

import bestplan.android.com.danstatistik.data.model.Subject
import bestplan.android.com.danstatistik.data.model.Table
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by gary.pierrelouis@sap.com on 12/05/2020.
 */
interface StatisticService {

    companion object {
        const val ENDPOINT = "https://api.statbank.dk/v1/"
    }

    @GET("tables/")
    suspend fun getTables(
        @Query("subjects") subject: String,
        @Query("lang") lang: String? = "en"
    ): Response<List<Table>>


    @GET("subjects/")
    suspend fun getSubjects(
        @Query("lang") lang: String? = "en"
    ): Response<List<Subject>>

}
