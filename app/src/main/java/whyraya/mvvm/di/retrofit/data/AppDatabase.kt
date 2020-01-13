package whyraya.mvvm.di.retrofit.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MyData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): MyDataDao
}