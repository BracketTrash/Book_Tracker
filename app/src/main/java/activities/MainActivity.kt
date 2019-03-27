package activities

import MainApp.MainApp
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.conor.book_tracker.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import models.Book_TrackerModel

class MainActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app : MainApp

    var book = Book_TrackerModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as MainApp

        btnAdd.setOnClickListener() {
            val bookTitle = bookTitle.text.toString()
            val bookAuthor = bookAuthor.text.toString()
            val bookPage = bookPage.text.toString()
            if (bookTitle.isNotEmpty() && bookAuthor.isNotEmpty() && bookPage.isNotEmpty() ) {
               app!!.books.add(book.copy())
                info("add Button Pressed: $bookTitle; $bookAuthor; $bookPage")
               app!!.books.forEach{info("add Button Pressed: ${it}")
                   setResult(AppCompatActivity.RESULT_OK)
                   finish()}
            }
            else {
                toast ("Please Enter a title")

    }
}}}
