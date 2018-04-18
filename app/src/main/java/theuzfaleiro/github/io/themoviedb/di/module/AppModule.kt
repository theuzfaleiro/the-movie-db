package theuzfaleiro.github.io.themoviedb.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Module(includes = [(AndroidInjectionModule::class)])
internal abstract class AppModule {

    @Binds
    @Singleton
    internal abstract fun application(app: Application): Application
}