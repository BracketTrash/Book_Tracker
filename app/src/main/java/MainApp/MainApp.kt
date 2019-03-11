package MainApp


import android.app.Application
import models.Book_TrackerModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp(var books: List<Book_TrackerModel>) : Application(), AnkoLogger {

    override fun onCreate() {
        super.onCreate()
        info("Book started")
        books.add(Book_TrackerModel("The Martian","Andy Weir", "432"))
        books.add(Book_TrackerModel("books", "Author", "page"))
    }
}