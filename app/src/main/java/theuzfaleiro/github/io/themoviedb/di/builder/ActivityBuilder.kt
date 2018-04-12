package theuzfaleiro.github.io.themoviedb.di.builder

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import theuzfaleiro.github.io.themoviedb.HomeActivity
import theuzfaleiro.github.io.themoviedb.di.scope.PerActivity

@Module(includes = [(AndroidInjectionModule::class)])
internal abstract class ActivityBuilder {

//    @PerActivity
//    @ContributesAndroidInjector(modules = [(HomeModule::class)])
//    internal abstract fun repositoryActivityInjector(): HomeActivity
}