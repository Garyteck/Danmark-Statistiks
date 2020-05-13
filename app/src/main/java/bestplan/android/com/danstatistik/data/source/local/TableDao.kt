package bestplan.android.com.danstatistik.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bestplan.android.com.danstatistik.data.model.Table

@Dao
interface TableDao {

    @Query("SELECT * FROM tables WHERE subjectId = :subjectId ")
    fun getTables(subjectId: String): LiveData<List<Table>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tables: List<Table>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(table: Table)
}
