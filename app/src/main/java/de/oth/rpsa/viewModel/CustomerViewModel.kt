package de.oth.rpsa.viewModel

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import de.oth.rpsa.repository.CustomerRepository
import de.oth.rpsa.data.RpsaDatabase
import de.oth.rpsa.model.Customer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CustomerViewModel(private val repository: CustomerRepository) : ViewModel(), Observable {

    val customer = repository.allCustomers

    @Bindable
    val inputLastName = MutableLiveData<String>()

    @Bindable
    val inputFirstName = MutableLiveData<String>()

    @Bindable
    val inputTelephone = MutableLiveData<String>()

    @Bindable
    val inputemail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {

        saveOrUpdateButtonText.value = "Save"

        clearAllOrDeleteButtonText.value = "Clear"

    }

    fun saveOrUpdate() {
        val lastName = inputLastName.value!!
        val firstName = inputFirstName.value!!
        val telephone = inputTelephone.value!!
        val email = inputemail.value!!

        addCustomer(Customer(0, lastName, firstName, Integer.parseInt(telephone), email))

        inputLastName.value = ""
        inputFirstName.value = ""
        inputTelephone.value = ""
        inputemail.value = ""

    }

    fun clearAllOrDelete() {
    }

    // add Customer in a background thread
    fun addCustomer(customer: Customer) = viewModelScope.launch {
        repository.addCustomer(customer)
    }

    fun updateCustomer(customer: Customer) = viewModelScope.launch {
        repository.updateCustomer(customer)
    }

    fun deleteCustomer(customer: Customer) = viewModelScope.launch {
        repository.deleteCustomer(customer)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}