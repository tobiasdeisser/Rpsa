package de.oth.rpsa.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.oth.rpsa.model.Customer
import de.oth.rpsa.model.Report

@Database(entities = [Customer::class, Report::class], version = 1, exportSchema = false)
abstract class RpsaDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun reportDao(): ReportDao

    // Singleton pattern so that only one instance of RoomDatabase can be created.
    companion object {
        @Volatile
        private var INSTANCE: RpsaDatabase? = null

        fun getDatabaseInstance(context: Context): RpsaDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            // Create new Instance if INSTANCE is null
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RpsaDatabase::class.java,
                    "Customer"
                ).build()
                INSTANCE = instance
                return instance
            }


        }
    }
}