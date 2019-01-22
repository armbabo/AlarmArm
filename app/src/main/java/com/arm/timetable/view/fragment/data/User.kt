package com.arm.timetable.view.fragment.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by harryle on 12/4/17.
 */
data class User(val id: Int = 0,
                @SerializedName("email") val email: String? = null,
                @SerializedName("nickname") val nickName: String? = null,
                @SerializedName("password") val password: String? = null,
                @SerializedName("password_confirm") val password_confirm: String? = null,
                @SerializedName("comment") val comment: String? = null,
                @SerializedName("avatar_url", alternate = ["avatar"]) val avatarUrl: String? = null,
                @SerializedName("auth_token") val auth_token: String = "",
                @SerializedName("is_blocked") val isBlocked: Boolean = false) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(email)
        parcel.writeString(nickName)
        parcel.writeString(password)
        parcel.writeString(password_confirm)
        parcel.writeString(comment)
        parcel.writeString(avatarUrl)
        parcel.writeString(auth_token)
        parcel.writeByte(if (isBlocked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}