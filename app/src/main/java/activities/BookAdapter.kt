package activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.conor.book_tracker.R
import kotlinx.android.synthetic.main.card_placement.view.*
import models.Book_TrackerModel

    interface BookListener {
    fun onBookClick(book: Book_TrackerModel)
}
    class BookAdapter constructor(private var books: List<Book_TrackerModel>,
                                  private val listener: BookListener) : RecyclerView.Adapter<BookAdapter.MainHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
            return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_placement, parent, false))
        }

        override fun onBindViewHolder(holder: MainHolder, position: Int) {
            val book = books[holder.adapterPosition]
            holder.bind(book, listener)
        }

        override fun getItemCount(): Int = books.size

        class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(book: Book_TrackerModel, listener: BookListener) {
                itemView.bookTitle.text = book.title
                itemView.bookAuthor.text = book.author
                itemView.bookPage.text = book.page
                itemView.setOnClickListener{listener.onBookClick(book)}
            }
        }
    }

