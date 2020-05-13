package bestplan.android.com.danstatistik.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bestplan.android.com.danstatistik.data.model.Subject
import bestplan.android.com.danstatistik.data.model.Table

const val DB_NAME = "danstat-db"

@Database(
    entities = [Table::class, Subject::class],
    version = 2, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tableDao(): TableDao
    abstract fun subjectDao(): SubjectDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance
                ?: synchronized(this) {
                    instance
                        ?: buildDatabase(
                            context
                        )
                            .also { instance = it }
                }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context, AppDatabase::class.java,
                DB_NAME
            )
                .fallbackToDestructiveMigration().build()
        }
    }
}