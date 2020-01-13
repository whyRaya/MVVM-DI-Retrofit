package whyraya.mvvm.di.retrofit.ui

import android.app.Application
import whyraya.mvvm.di.retrofit.api.APIServices
import whyraya.mvvm.di.retrofit.di.BaseViewModel
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var api: APIServices

    private val repository = MainRepository(application)

    val loading = repository.getLoading()

    val message = repository.getMessage()

    val myData = repository.getMyData()

    init{
        getData()
    }

    fun getData() {
        repository.getData(api.getPosts())
    }

    override fun onCleared() {
        super.onCleared()
        repository.onCleared()
    }
}