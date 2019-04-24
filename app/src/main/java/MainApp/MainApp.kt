package MainApp


import android.app.Application
import models.BookMemStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp: Application(), AnkoLogger {

    val books = BookMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Book started")
    }
}

