package bestplan.android.com.danstatistik.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bestplan.android.com.danstatistik.data.model.Subject

@Dao
interface SubjectDao {

    @Query("SELECT * FROM subjects ")
    fun getSubjects(): LiveData<List<Subject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(subjects: List<Subject>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subject: Subject)

    @Query("SELECT  * FROM subjects  WHERE id = :subjectId ")
    fun getSubject(subjectId: String): LiveData<Subject>
}
