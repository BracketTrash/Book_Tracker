package activities

import MainApp.MainApp
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.conor.book_tracker.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import models.Book_TrackerModel

class MainActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as MainApp

        btnAdd.setOnClickListener() {
            val bookTitle = bookTitle.text.toString()
            val bookAuthor = bookAuthor.text.toString()
            val bookPage = bookPage.text.toString()
            if (bookTitle.isNotEmpty() && bookAuthor.isNotEmpty() && bookPage.isNotEmpty() ) {
              //  app.booklist.add(book.copy())
                info("add Button Pressed: $bookTitle; $bookAuthor; $bookPage")
               // app.booklist.forEach{info("add Button Pressed: ${it}")}
            }
            else {
                toast ("Please Enter a title")

    }
}}}
