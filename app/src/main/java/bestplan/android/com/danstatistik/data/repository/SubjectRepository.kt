package bestplan.android.com.danstatistik.data.repository

import bestplan.android.com.danstatistik.api.BaseDataSource
import bestplan.android.com.danstatistik.data.resultLiveData
import bestplan.android.com.danstatistik.data.source.local.SubjectDao
import bestplan.android.com.danstatistik.data.source.remote.SubjectRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubjectRepository @Inject constructor(
    private val subjectRemoteDataSource: SubjectRemoteDataSource,
    private val subjectDao: SubjectDao
) : BaseDataSource() {

    fun getSubject(subjectId: String) = subjectDao.getSubject(subjectId)

    fun getSubjects() = resultLiveData(
        databaseQuery = { subjectDao.getSubjects() },
        networkCall = { subjectRemoteDataSource.fetchSubjects() },
        saveCallResult = { subjectDao.insertAll(it) }
    )


}