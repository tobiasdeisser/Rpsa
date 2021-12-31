package de.oth.rpsa.model

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer")
data class Customer (
        @PrimaryKey (autoGenerate = true)
        val id: Int,
        val lastName: String,
        val firstName: String,
        val telephone: Int,
        val eMail: String
       // val isAdmin: Boolean,
       // val adminID: Int,
       // val password: String
        )