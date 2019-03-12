package MainApp


import android.app.Application
import models.Book_TrackerModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp: Application(), AnkoLogger {
   //~##########  var books: List<Book_TrackerModel>

    override fun onCreate() {
        super.onCreate()
        info("Book started")
       // books.add(Book_TrackerModel("The Martian","Andy Weir", "432"))
       // books.add(Book_TrackerModel("books", "Author", "332"))
    }
}

