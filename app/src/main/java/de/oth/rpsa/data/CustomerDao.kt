package de.oth.rpsa.data

import androidx.lifecycle.LiveData
import androidx.room.*
import de.oth.rpsa.model.Customer

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCustomer(customer: Customer) : Long

    @Update
    suspend fun updateCustomer(customer: Customer) : Int

    @Delete
    suspend fun deleteCustomer(customer: Customer) : Int

    @Query("SELECT * FROM Customer ORDER BY id")
    fun getAllCustomers(): LiveData<List<Customer>>

    @Query("DELETE FROM Customer")
    suspend fun deleteAllCustomers() : Int

}