package whyraya.mvvm.di.retrofit.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import whyraya.mvvm.di.retrofit.ui.MainViewModel

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val component: MainComponent = DaggerMainComponent
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainViewModel -> component.inject(this)
        }
    }
}