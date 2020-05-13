package bestplan.android.com.danstatistik.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey
    val id: String = "",
    val active: Boolean = false,
    val description: String = "",
    val hasSubjects: Boolean = false
)