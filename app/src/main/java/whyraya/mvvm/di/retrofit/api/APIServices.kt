package whyraya.mvvm.di.retrofit.api

import io.reactivex.Observable
import retrofit2.http.GET
import whyraya.mvvm.di.retrofit.data.MyData

interface APIServices {

    @GET("/posts")
    fun getPosts(): Observable<List<MyData>>
}