package models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book_TrackerModel(var id:Long=0,var title: String = "", var author: String = "", var page: String = "" ): Parcelable

var image: String = ""