package de.oth.rpsa.viewModel

import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import de.oth.rpsa.event.Event
import de.oth.rpsa.repository.CustomerRepository
import de.oth.rpsa.model.Customer
import kotlinx.coroutines.launch

class CustomerViewModel(private val repository: CustomerRepository) : ViewModel(), Observable {

    val customer = repository.allCustomers
    private var isUpdateOrDelete = false
    private lateinit var customerToUpdateOrDelete: Customer

    @Bindable
    val inputLastName = MutableLiveData<String>()

    @Bindable
    val inputFirstName = MutableLiveData<String>()

    @Bindable
    val inputTelephone = MutableLiveData<String>()

    @Bindable
    val inputemail = MutableLiveData<String>()

    @Bindable
    val inputVehicle = MutableLiveData<String>()

    @Bindable
    val addOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        addOrUpdateButtonText.value = "Add"
        clearAllOrDeleteButtonText.value = "Clear"

    }

    fun addOrUpdate() {
        if (inputLastName.value == null) {
            statusMessage.value = Event("Enter a last name")
        } else if (inputFirstName.value == null) {
            statusMessage.value = Event("Enter a first name")
        } else if (inputTelephone.value == null) {
            statusMessage.value = Event("Enter a telephone number")
        } else if (inputemail.value == null) {
            statusMessage.value = Event("Enter an email address")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputemail.value).matches()) {
            statusMessage.value = Event("the entered email address is not valid")
        } else {
            if (isUpdateOrDelete) {
                customerToUpdateOrDelete.lastName = inputLastName.value!!
                customerToUpdateOrDelete.firstName = inputFirstName.value!!
                customerToUpdateOrDelete.telephone = inputTelephone.value!!.toLong()
                customerToUpdateOrDelete.eMail = inputemail.value!!
                customerToUpdateOrDelete.vehicle = inputVehicle.value!!

                updateCustomer(customerToUpdateOrDelete)
            } else {
                val lastName = inputLastName.value!!
                val firstName = inputFirstName.value!!
                val telephone = (inputTelephone.value!!).toLong()
                val email = inputemail.value!!
                val vehicle = inputVehicle.value!!
                addCustomer(
                    Customer(
                        0,
                        lastName,
                        firstName,
                        telephone,
                        email,
                        vehicle
                    )
                )

                inputLastName.value = ""
                inputFirstName.value = ""
                inputTelephone.value = ""
                inputemail.value = ""
            }

        }

    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            deleteCustomer(customerToUpdateOrDelete)
        } else {
            clearAll()
        }

    }

    private fun clearAll() = viewModelScope.launch {
        val rowsDeleted = repository.deleteAllCustomers()
        if (rowsDeleted > 0) {
            statusMessage.value = Event("$rowsDeleted Customers deleted successfully")

        } else {
            statusMessage.value = Event("An error occurred while deleting all Customers")

        }

    }

    fun initUpdateAndDeleteButton(customer: Customer) {
        inputLastName.value = customer.lastName
        inputFirstName.value = customer.firstName
        inputTelephone.value = customer.telephone.toString()
        inputemail.value = customer.eMail
        inputVehicle.value = customer.vehicle

        isUpdateOrDelete = true
        customerToUpdateOrDelete = customer
        addOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }


    // add Customer in a background thread
    fun addCustomer(customer: Customer) = viewModelScope.launch {
        val newRowAdded = repository.addCustomer(customer)
        if (newRowAdded > -1) {
            statusMessage.value = Event("New customer added successfully")

        } else {
            statusMessage.value = Event("Oops something wrong")

        }

        statusMessage.value = Event("New customer added successfully")
    }

    fun updateCustomer(customer: Customer) = viewModelScope.launch {
        val numberOfRowsUpdated = repository.updateCustomer(customer)
        if (numberOfRowsUpdated > 0) {
            inputLastName.value = ""
            inputFirstName.value = ""
            inputTelephone.value = ""
            inputemail.value = ""
            isUpdateOrDelete = false
            addOrUpdateButtonText.value = "Add"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$numberOfRowsUpdated Customer updated successfully")
        } else {
            statusMessage.value = Event("An error occurred while updating")

        }


    }

    fun deleteCustomer(customer: Customer) = viewModelScope.launch {
        val numberOfRowsDeleted = repository.deleteCustomer(customer)

        if (numberOfRowsDeleted > 0) {
            inputLastName.value = ""
            inputFirstName.value = ""
            inputTelephone.value = ""
            inputemail.value = ""

            isUpdateOrDelete = false
            addOrUpdateButtonText.value = "Add"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$numberOfRowsDeleted Customer deleted successfully")
        } else {
            statusMessage.value = Event("an error occurred while deleting")

        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}