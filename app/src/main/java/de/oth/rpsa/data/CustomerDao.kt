package de.oth.rpsa.data

import androidx.lifecycle.LiveData
import androidx.room.*
import de.oth.rpsa.model.Customer

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCustomer(customer: Customer)

    @Update
    suspend fun updateCustomer(customer: Customer)

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Query("SELECT * FROM Customer ORDER BY id")
    fun getAllCustomers(): LiveData<List<Customer>>

}