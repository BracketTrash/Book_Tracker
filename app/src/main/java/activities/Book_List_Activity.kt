package activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.startActivityForResult
import kotlinx.android.synthetic.main.activity_book_list.*
import kotlinx.android.synthetic.main.card_placement.view.*
import com.conor.book_tracker.R
import MainApp.MainApp
import android.view.*
import org.jetbrains.anko.startActivityForResult
import models.Book_TrackerModel

 class BookListActivity : AppCompatActivity(){
    lateinit var app:MainApp

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)
        app=application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = BookAdapter(app.books)


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
}

class BookAdapter constructor(private var books: List<Book_TrackerModel>) : RecyclerView.Adapter<BookAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_placement, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val book = books[holder.adapterPosition]
        holder.bind(book)
    }

    override fun getItemCount(): Int = books.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(book: Book_TrackerModel) {
            itemView.bookTitle.text = book.title
            itemView.bookAuthor.text = book.author
            itemView.bookPage.text = book.page
        }
    }
}
