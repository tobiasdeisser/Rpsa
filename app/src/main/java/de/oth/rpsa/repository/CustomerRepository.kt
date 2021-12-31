package de.oth.rpsa.repository

import androidx.lifecycle.LiveData
import de.oth.rpsa.data.CustomerDao
import de.oth.rpsa.model.Customer

class CustomerRepository (private val customerDao: CustomerDao){


    val allCustomers: LiveData<List<Customer>> = customerDao.getAllCustomers()

    suspend fun addCustomer(customer: Customer){
        customerDao.addCustomer(customer)
    }

    suspend fun updateCustomer(customer: Customer){
        customerDao.updateCustomer(customer)
    }

    suspend fun deleteCustomer(customer: Customer){
        customerDao.deleteCustomer(customer)
    }

}