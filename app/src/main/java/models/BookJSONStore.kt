package models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import helpers.*
import java.util.*

val JSON_FILE = "books.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<Book_TrackerModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class BookJSONStore : BookStore, AnkoLogger {

    val context: Context
    var books = mutableListOf<Book_TrackerModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<Book_TrackerModel> {
        return books
    }

    override fun create(book: Book_TrackerModel) {
        book.id = generateRandomId()
        books.add(book)
        serialize()
    }

    override fun delete(book: Book_TrackerModel) {
        books.remove(book)
        serialize()
    }

    override fun update(book: Book_TrackerModel) {
        val bookList = findAll() as ArrayList<Book_TrackerModel>
        var foundbook: Book_TrackerModel? = bookList.find { p -> p.id == book.id }
        if (foundbook != null) {
            foundbook.title = book.title
            foundbook.author = book.author
            foundbook.page = book.page
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(books, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        books = Gson().fromJson(jsonString, listType)
    }
}