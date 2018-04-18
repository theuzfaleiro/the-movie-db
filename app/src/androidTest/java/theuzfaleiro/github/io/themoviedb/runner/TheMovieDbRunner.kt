package theuzfaleiro.github.io.themoviedb.runner

import android.app.Application
import android.content.Context
import io.appflate.restmock.android.RESTMockTestRunner
import theuzfaleiro.github.io.themoviedb.TheMovieDbTestApplication

class TheMovieDbRunner : RESTMockTestRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TheMovieDbTestApplication::class.java.name, context)
    }
}