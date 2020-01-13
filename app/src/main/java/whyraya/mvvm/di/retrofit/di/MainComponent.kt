package whyraya.mvvm.di.retrofit.di

import dagger.Component
import whyraya.mvvm.di.retrofit.ui.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface MainComponent {

    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun build(): MainComponent

        fun networkModule(networkModule: NetworkModule): Builder
    }
}