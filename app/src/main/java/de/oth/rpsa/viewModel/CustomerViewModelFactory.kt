package de.oth.rpsa.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.oth.rpsa.repository.CustomerRepository
import java.lang.IllegalArgumentException

class CustomerViewModelFactory(private val customerRepository: CustomerRepository) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            return CustomerViewModel(customerRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }


}