package bestplan.android.com.danstatistik.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bestplan.android.com.danstatistik.di.ViewModelFactory
import bestplan.android.com.danstatistik.di.ViewModelKey
import bestplan.android.com.danstatistik.ui.viewmodel.SubjectViewModel
import bestplan.android.com.danstatistik.ui.viewmodel.TableViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TableViewModel::class)
    abstract fun bindTableViewModel(viewModel: TableViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SubjectViewModel::class)
    abstract fun bindSubjectViewModel(viewModel: SubjectViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
