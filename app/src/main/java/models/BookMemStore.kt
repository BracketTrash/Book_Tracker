package models

import com.google.firebase.database.core.Constants
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import com.conor.book_tracker.R

var lastId =0L

internal fun getId(): Long {
    return lastId++
}

class BookMemStore : BookStore , AnkoLogger{

    val books = ArrayList<Book_TrackerModel>()

    override fun findAll(): List<Book_TrackerModel> {
        return books
    }

    override fun create(book: Book_TrackerModel) {
        book.id=getId()
        books.add(book)
        logAll()
    }

    fun delete(book: Book_TrackerModel) {
        books.remove(book)
    }

     override fun update(book: Book_TrackerModel){
        var foundBook: Book_TrackerModel?=books.find {p->p.id==book.id}
        if(foundBook != null) {
            foundBook.title = book.title
            foundBook.author = book.author
            foundBook.page = book.page
            logAll()
        }
    }

    fun logAll() {
        books.forEach{ info("${it}")
        }
    }
}