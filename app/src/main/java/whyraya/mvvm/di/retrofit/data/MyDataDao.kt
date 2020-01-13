package whyraya.mvvm.di.retrofit.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MyDataDao {

    @get:Query("SELECT * FROM mydata")
    val all: List<MyData>

    @Insert
    fun insertAll(vararg data: MyData)

}