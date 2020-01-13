package whyraya.mvvm.di.retrofit.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import whyraya.mvvm.di.retrofit.api.APIServices


@Module
object NetworkModule {

    private const val BASE_URL: String = "https://jsonplaceholder.typicode.com"

    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): APIServices {
        return retrofit.create(APIServices::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}