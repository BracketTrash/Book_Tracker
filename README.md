# Book Tracker

    The app is composed of two screens. The first screen displays a list of books, in which, each book is described by its title, author and page number.
    After a user selects a book from the list, a second screen appears displaying book details which can then be updated. 
    The same activity can be used for creation also.

    ## Overview
    
    The app does the following:
    1. Displays list of created books
    2. Allows the user to view,create,update and delete their books
    3. Allows the books to stored to json

    To achieve this, there are ten different components in this app:
    
    1. `MainActivity` - Responsible for executing the crud requests and retrieving the JSON.
    2. `Book_TrackerModel` - Model object responsible for encapsulating the attributes for each individual book.
    3. `BookAdapter` - Responsible for mapping each `Book` to a particular view layout.
    4. `BookListActivity` - Responsible for fetching and deserializing the data.
    5. `FileHelpers` - Collection of methods for interacting with JSON.
    6. `ImageHelpers` - Collection of methods for interacting with Images.
    7. `MainApp` - Starts the application and JSON
    8. `BookJSONStore` - Methods for JSON crud and manipulating JSON.
    9. `BookMemStore` - Methods for Array crud and manipulating book Array.
    10. `BookStore` - Interface for using crud functions.
