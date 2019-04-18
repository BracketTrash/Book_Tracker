package activities

import MainApp.MainApp
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.conor.book_tracker.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import models.Book_TrackerModel
import helpers.ImageHelpers

class MainActivity : AppCompatActivity(), AnkoLogger {
    val IMAGE_REQUEST = 1

    lateinit var app : MainApp

    var edit = false

    var book = Book_TrackerModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        app = application as MainApp

        if (intent.hasExtra("book_edit")) {
            //crash on opening of book caused by below line
            edit=true
            book = intent.extras.getParcelable<Book_TrackerModel>("placemark_edit")
            bookTitle.setText(book.title)
            bookAuthor.setText(book.author)
            bookPage.setText(book.page)
            btnAdd.setText(R.string.save_book)
        }

        if (intent.hasExtra("book_edit")) {
            //... as before
            bookImage.setImageBitmap(readImageFromPath(this, book.image))
        }

        btnAdd.setOnClickListener() {
            val bookTitle = bookTitle.text.toString()
            val bookAuthor = bookAuthor.text.toString()
            val bookPage = bookPage.text.toString()
            if (bookTitle.isEmpty() && bookAuthor.isEmpty() && bookPage.isEmpty()) {
             toast(R.string.enter_book_title)
            } else {
                if (edit) {
                    app.books.update(book.copy())
                } else {
                    app.books.create(book.copy())
                }
            }
                info("add Button Pressed: $bookTitle; $bookAuthor; $bookPage")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            when (requestCode) {
                IMAGE_REQUEST -> {
                    if (data != null) {
                        book.image = data.getData().toString()
                        bookImage.setImageBitmap(readImage(this, resultCode, data))
                    }
            }
        }
}
    }
}
