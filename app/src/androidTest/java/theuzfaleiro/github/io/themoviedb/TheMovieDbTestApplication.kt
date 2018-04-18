package theuzfaleiro.github.io.themoviedb

import io.appflate.restmock.RESTMockServer

class TheMovieDbTestApplication : TheMovieDbApplication() {

    override fun getBaseUrl(): String {
        return RESTMockServer.getUrl()
    }
}