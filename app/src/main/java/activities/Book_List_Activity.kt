package activities

import MainApp.MainApp
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.conor.book_tracker.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_book_list.*
import models.Book_TrackerModel
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult


class BookListActivity : AppCompatActivity(), BookListener{

    lateinit var app:MainApp

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(com.conor.book_tracker.R.layout.activity_book_list)
        app=application as MainApp


//        recyclerView.adapter = BookAdapter(app.books.findAll(), this)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadBooks()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.conor.book_tracker.R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<MainActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

     override fun onBookClick(book: Book_TrackerModel) {
         startActivityForResult(intentFor<MainActivity>().putExtra("book_edit", book), 0)
     }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
      //   recyclerView is a widget in Book_List_Activity
       //  recyclerView.adapter?.notifyDataSetChanged()
         loadBooks()
         super.onActivityResult(requestCode, resultCode, data)
     }

     fun loadBooks() {
         showBooks(app.books.findAll())
     }

     fun showBooks (books: List<Book_TrackerModel>) {
         recyclerView.adapter = BookAdapter(books, this)
         recyclerView.adapter?.notifyDataSetChanged()
     }
}
