package hr.tvz.android.listamarinic

import android.media.Image
import android.os.Parcelable
import android.widget.ImageView
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Crypto(val name:String, val price: Double, val supply: Long): Parcelable {}