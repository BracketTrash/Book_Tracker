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

     fun delete(book: Book_TrackerModel) {
        books.remove(book)
        serialize()
    }

    override fun update(book: Book_TrackerModel) {
        // todo
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