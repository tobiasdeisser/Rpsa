package de.oth.rpsa.repository

import androidx.lifecycle.LiveData
import de.oth.rpsa.data.CustomerDao
import de.oth.rpsa.model.Customer

class CustomerRepository (private val customerDao: CustomerDao){


    val allCustomers: LiveData<List<Customer>> = customerDao.getAllCustomers()

    suspend fun addCustomer(customer: Customer) : Long{
        return customerDao.addCustomer(customer)
    }

    suspend fun updateCustomer(customer: Customer) : Int{
        return customerDao.updateCustomer(customer)
    }

    suspend fun deleteCustomer(customer: Customer) : Int{
        return customerDao.deleteCustomer(customer)
    }

    suspend fun deleteAllCustomers() : Int{
        return customerDao.deleteAllCustomers()
    }

}