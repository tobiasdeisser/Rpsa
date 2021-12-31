package de.oth.rpsa.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import de.oth.rpsa.repository.CustomerRepository
import de.oth.rpsa.data.RpsaDatabase
import de.oth.rpsa.model.Customer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application) : AndroidViewModel(application) {

    private val readData: LiveData<List<Customer>>
    private val repository : CustomerRepository
    init {
        val customerDao = RpsaDatabase.getDatabaseInstance(application).customerDao()
        repository = CustomerRepository(customerDao)
        readData = repository.readData

    }

    fun addCustomer(customer: Customer){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCustomer(customer)
        }

    }
}