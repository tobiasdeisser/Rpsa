package de.oth.rpsa.data

import androidx.lifecycle.LiveData
import androidx.room.*
import de.oth.rpsa.model.Report

@Dao
interface ReportDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addReport(report: Report)

    @Update
    suspend fun updateReport(report: Report)

    @Delete
    suspend fun deleteReport(report: Report)

    @Query("SELECT * FROM Report ORDER BY reportID")
    fun readData(): LiveData<List<Report>>


}