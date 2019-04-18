package models

interface BookStore {
    fun findAll(): List<Book_TrackerModel>
    fun create(placemark: Book_TrackerModel)
}