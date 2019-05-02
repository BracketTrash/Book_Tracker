package MainApp


import android.app.Application
import models.BookMemStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import models.BookStore
import models.BookJSONStore


class MainApp : Application(), AnkoLogger {

    lateinit var books: BookStore

    override fun onCreate() {
        super.onCreate()
        books = BookJSONStore(applicationContext)
        info("library started")
    }
}
