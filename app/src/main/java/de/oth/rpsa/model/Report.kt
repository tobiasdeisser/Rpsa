package de.oth.rpsa.model

import android.location.Location
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity (tableName = "Report")
class Report(
    @PrimaryKey (autoGenerate = true)
    val reportID: Int,
    val imageFilePath: String,
    val description: String,
    val email: String,
)