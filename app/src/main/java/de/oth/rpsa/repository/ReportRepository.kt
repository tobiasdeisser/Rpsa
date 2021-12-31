package de.oth.rpsa.repository

import androidx.lifecycle.LiveData
import de.oth.rpsa.data.ReportDao
import de.oth.rpsa.model.Report

class ReportRepository (private val reportDao: ReportDao) {

    val readData: LiveData<List<Report>> = reportDao.readData()

    suspend fun addReport(report: Report){
        reportDao.addReport(report)
    }

    suspend fun updateReport(report: Report){
        reportDao.updateReport(report)
    }

    suspend fun deleteReport(report: Report){
        reportDao.deleteReport(report)
    }



}