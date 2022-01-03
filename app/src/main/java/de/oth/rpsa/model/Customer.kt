package de.oth.rpsa.model

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer")
data class Customer (
        @PrimaryKey (autoGenerate = true)
        var id: Int,
        var lastName: String,
        var firstName: String,
        var telephone: Long,
        var eMail: String,
        var vehicle:String

        )