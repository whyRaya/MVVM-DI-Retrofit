package whyraya.mvvm.di.retrofit.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import whyraya.mvvm.di.retrofit.data.AppDatabase
import whyraya.mvvm.di.retrofit.data.MyData
import whyraya.mvvm.di.retrofit.data.MyDataDao

class MainRepository(app: Application){

    private val loading = MutableLiveData<Boolean>(false)

    private val message: MutableLiveData<String> = MutableLiveData()

    private val data = MutableLiveData<List<MyData>>()

    private val db = Room.databaseBuilder(app, AppDatabase::class.java, "my_data").build()

    private val myDataDao: MyDataDao = db.postDao()

    private lateinit var subscription: Disposable

    fun getLoading(): LiveData<Boolean> = loading

    fun getMessage(): LiveData<String> = message

    fun getMyData(): LiveData<List<MyData>> = data

    fun getData(api: Observable<List<MyData>>){
        subscription = Observable.fromCallable { myDataDao.all }.concatMap { myData ->
            if(myData.isEmpty())
                api.concatMap { data ->
                    myDataDao.insertAll(*data.toTypedArray())
                    Observable.just(data)
                }
            else
                Observable.just(myData)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loading.postValue(true) }
            .doOnTerminate { loading.postValue(false) }
            .subscribe(
                { result -> data.value = result },
                { message.value = it.message }
            )
    }

    fun onCleared() {
        subscription.dispose()
    }

}