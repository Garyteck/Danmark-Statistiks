package bestplan.android.com.danstatistik.di.component

import android.app.Application
import bestplan.android.com.danstatistik.App
import bestplan.android.com.danstatistik.di.modules.AppModule
import bestplan.android.com.danstatistik.di.modules.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        AppModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}
