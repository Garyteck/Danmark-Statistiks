package bestplan.android.com.danstatistik.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "tables", foreignKeys = [ForeignKey(
        entity = Subject::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("subjectId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Table(
    @PrimaryKey
    val id: String = "",
    @ColumnInfo(name = "subjectId", index = true)
    var subjectId: String = "",
    val active: Boolean = false,
    val firstPeriod: String? = "",
    val latestPeriod: String? = "",
    val text: String? = "",
    val unit: String? = "",
    val updated: String? = ""
)