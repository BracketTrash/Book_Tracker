package MainApp


import android.app.Application
import models.BookMemStore
import models.Book_TrackerModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp: Application(), AnkoLogger {

    val books = BookMemStore()
    override fun onCreate() {
        super.onCreate()
        val books = BookMemStore()
        info("Book started")
//        books.add(Book_TrackerModel("The Martian","Andy Weir", "432"))
//        books.add(Book_TrackerModel("books", "Author", "332"))
//        books.add(Book_TrackerModel("books", "Author", "332"))
//        books.add(Book_TrackerModel("books", "Author", "332"))
//        books.add(Book_TrackerModel("books", "Author", "332"))
    }
}

