package bestplan.android.com.danstatistik.di.modules

import android.app.Application
import bestplan.android.com.danstatistik.api.StatisticService
import bestplan.android.com.danstatistik.data.source.local.AppDatabase
import bestplan.android.com.danstatistik.data.source.remote.SubjectRemoteDataSource
import bestplan.android.com.danstatistik.data.source.remote.TableRemoteDataSource
import bestplan.android.com.danstatistik.di.StatisticApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {


    @Singleton
    @Provides
    fun provideStatisticsService(
        @StatisticApi okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, StatisticService::class.java)

    @Singleton
    @Provides
    fun provideTableRemoteDataSource(service: StatisticService) =
        TableRemoteDataSource(
            service
        )

    @Singleton
    @Provides
    fun provideSubjectRemoteDataSource(service: StatisticService) =
        SubjectRemoteDataSource(
            service
        )

    @StatisticApi
    @Provides
    fun providePrivateOkHttpClient(
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder().build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideTableDao(db: AppDatabase) = db.tableDao()

    @Singleton
    @Provides
    fun provideSubjectDao(db: AppDatabase) = db.subjectDao()


    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(StatisticService.ENDPOINT)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory, clazz: Class<T>
    ): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}
