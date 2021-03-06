package bestplan.android.com.danstatistik.data.source.remote

import bestplan.android.com.danstatistik.api.BaseDataSource
import bestplan.android.com.danstatistik.api.StatisticService
import javax.inject.Inject


class TableRemoteDataSource @Inject constructor(private val service: StatisticService) :
    BaseDataSource() {


    suspend fun fetchTables(subject: String) =
        getResult { service.getTables(subject) }

}