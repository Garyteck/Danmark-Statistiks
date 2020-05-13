package bestplan.android.com.danstatistik.di.modules

import bestplan.android.com.danstatistik.ui.view.SubjectFragment
import bestplan.android.com.danstatistik.ui.view.TableFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeTableFragment(): TableFragment

    @ContributesAndroidInjector
    abstract fun contributeSubjectFragment(): SubjectFragment
}
