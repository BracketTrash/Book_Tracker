package models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId =0L

internal fun getId(): Long {
    return lastId++
}

class BookMemStore : BookStore , AnkoLogger{

    override fun create(book: Book_TrackerModel) {
        book.id=getId()
        books.add(book)
        logAll()
    }

     fun update(book: Book_TrackerModel){
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

    val books = ArrayList<Book_TrackerModel>()

    override fun findAll(): List<Book_TrackerModel> {
        return books
    }


}