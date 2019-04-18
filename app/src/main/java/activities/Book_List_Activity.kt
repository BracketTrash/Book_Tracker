package activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import org.jetbrains.anko.startActivityForResult
import kotlinx.android.synthetic.main.activity_book_list.*
import com.conor.book_tracker.R
import MainApp.MainApp
import android.content.Intent
import android.view.*
import models.Book_TrackerModel
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult

class BookListActivity : AppCompatActivity(), BookListener{

    lateinit var app:MainApp

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)
        app=application as MainApp


        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadBooks()

        toolbarMain.title=title
        setSupportActionBar(toolbarMain)
    }
     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.menu_main, menu)
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
         //recyclerView is a widget in Book_List_Activity
         loadBooks()
         super.onActivityResult(requestCode, resultCode, data)
     }

     private fun loadBooks() {
         showBooks(app.books.findAll())
     }

     fun showBooks (books: List<Book_TrackerModel>) {
         recyclerView.adapter = BookAdapter(books, this)
         recyclerView.adapter?.notifyDataSetChanged()
     }
}
