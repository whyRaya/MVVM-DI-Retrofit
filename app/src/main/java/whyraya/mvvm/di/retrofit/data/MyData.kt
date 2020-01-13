package whyraya.mvvm.di.retrofit.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyData (
    val userId: Int,
    @field:PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)