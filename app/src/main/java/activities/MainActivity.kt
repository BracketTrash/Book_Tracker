package activities

//import com.google.android.gms.tasks.Task
import MainApp.MainApp
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.conor.book_tracker.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import models.Book_TrackerModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), AnkoLogger {
    //private StorageReference mStorageRef
    lateinit var app : MainApp
    var edit = false
    var book = Book_TrackerModel()
    val IMAGE_REQUEST = 1

    lateinit var _db: DatabaseReference

    fun addTask(){

        //Declare and Initialise the Task
        val task = Task.create()

        //Set Task Description and isDone Status
        task.taskDesc = bookTitle.text.toString()
        task.done = false

        //Get the object id for the new task from the Firebase Database
        val newTask = _db.child(Statics.FIREBASE_TASK).push()
        task.objectId = newTask.key

        //Set the values for new task in the firebase using the footer form
        newTask.setValue(task)

        Toast.makeText(this, "Task added to the list successfully" + task.objectId, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

       _db = FirebaseDatabase.getInstance().getReference();
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
            if (book.title.isNotEmpty() && book.author.isNotEmpty() && book.page.isNotEmpty()) {
                if(edit){
                    app.books.update(book.copy())
                    addTask()
                }
                else {
                    app.books.create(book.copy())
                    addTask()
                }
                info("add Button Pressed: ${book.title} ${book.author} ${book.page}")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else {
                toast(R.string.enter_book_title)
            }
            }

        fun onOptionsItemSelected(item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.item_add -> startActivityForResult<MainActivity>(0)
            }
            return super.onOptionsItemSelected(item)
        }


         fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            when (requestCode) {
                IMAGE_REQUEST -> {
                    if (data != null) {
                        book.image = data.getData().toString()
                     //   bookImage.setImageBitmap(readImage(this, resultCode, data))
                    }
            }
        }
}
    }
}

