package com.example.firstproject.data.remote

import android.os.Parcel
import android.os.Parcelable

data class getAddressResponse(
    val addresses: List<Addresse>,
    val message: String,
    val status: Int
)

data class Addresse(
    val address_id: String,
    val title: String,
    val address: String
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address_id)
        parcel.writeString(title)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Addresse> {
        override fun createFromParcel(parcel: Parcel): Addresse {
            return Addresse(parcel)
        }

        override fun newArray(size: Int): Array<Addresse?> {
            return arrayOfNulls(size)
        }
    }
}
