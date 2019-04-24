package activities

import MainApp.MainApp
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.conor.book_tracker.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import models.Book_TrackerModel
import org.jetbrains.anko.startActivityForResult

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
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
            book = intent.extras.getParcelable<Book_TrackerModel>("book_edit")
            bookTitle.setText(book.title)
            bookAuthor.setText(book.author)
            bookPage.setText(book.page)
            btn_add.setText(R.string.save_book)
            edit=true
        }

        btn_add.setOnClickListener() {
             book.title = bookTitle.text.toString()
             book.author = bookAuthor.text.toString()
             book.page = bookPage.text.toString()
            if (bookTitle.isNotEmpty() && bookAuthor.isNotEmpty() && bookPage.isNotEmpty()) {
                if(edit){
                    app.books.update(book.copy())
                }
                else {
                    app.books.create(book.copy())
                }
                info("add Button Pressed: ${book.title} ${book.author} ${book.page}")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            }

        fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(com.conor.book_tracker.R.menu.menu_main, menu)
            return super.onCreateOptionsMenu(menu)
        }

        fun onOptionsItemSelected(item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.item_add -> startActivityForResult<MainActivity>(0)
            }
            return super.onOptionsItemSelected(item)
        }

//        chooseImage.setOnClickListener {
//            showImagePicker(this, IMAGE_REQUEST)
//        }

         fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            when (requestCode) {
                IMAGE_REQUEST -> {
                    if (data != null) {
                       // book.image = data.getData().toString()
                       // bookImage.setImageBitmap(readImage(this, resultCode, data))
                    }
            }
        }
}
    }
}

