package models

interface BookStore {
    fun findAll(): List<Book_TrackerModel>
    fun create(book: Book_TrackerModel)
    fun update(book: Book_TrackerModel)
}