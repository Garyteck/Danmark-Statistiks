package bestplan.android.com.danstatistik.data.repository

import bestplan.android.com.danstatistik.api.BaseDataSource
import bestplan.android.com.danstatistik.data.resultLiveData
import bestplan.android.com.danstatistik.data.source.local.TableDao
import bestplan.android.com.danstatistik.data.source.remote.TableRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val tableRemoteDataSource: TableRemoteDataSource,
    private val tableDao: TableDao
) : BaseDataSource() {


    fun getTables(subjectId: String) = resultLiveData(
        databaseQuery = { tableDao.getTables(subjectId) },
        networkCall = { tableRemoteDataSource.fetchTables(subjectId) },
        saveCallResult = {
            it.onEach { it.subjectId = subjectId }
            tableDao.insertAll(it)
        }
    )


}