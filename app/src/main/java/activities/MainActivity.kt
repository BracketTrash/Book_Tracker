package activities

//import com.google.android.gms.tasks.Task
import MainApp.MainApp
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.conor.book_tracker.R
import kotlinx.android.synthetic.main.activity_main.*
import models.Book_TrackerModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import android.view.*

class MainActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app : MainApp
    var edit = false
    var book = Book_TrackerModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//       toolbarAdd.title = title
//       setSupportActionBar(toolbarAdd)

        app = application as MainApp

        if (intent.hasExtra("book_edit")) {
            edit = true
            book = intent.extras.getParcelable<Book_TrackerModel>("book_edit")
            bookTitle.setText(book.title)
            bookAuthor.setText(book.author)
            bookPage.setText(book.page)
            btn_add.setText(R.string.save_book)

        }

        btn_add.setOnClickListener() {
            book.title = bookTitle.text.toString()
            book.author = bookAuthor.text.toString()
            book.page = bookPage.text.toString()
            if (book.title.isNotEmpty() && book.author.isNotEmpty() && book.page.isNotEmpty()) {
                if (edit) {
                    app.books.update(book.copy())

                } else {
                    app.books.create(book.copy())
                }
                info("add Button Pressed: ${book.title} ${book.author} ${book.page}")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast(R.string.enter_book_title)
            }
        }

    }

      override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(com.conor.book_tracker.R.menu.menu_book, menu)
            if (edit && menu != null) menu.getItem(0).setVisible(true)
            return super.onCreateOptionsMenu(menu)
        }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.books.delete(book)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

       override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

        }
    }





