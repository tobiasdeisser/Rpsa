package de.oth.rpsa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.oth.rpsa.adapter.CustomerRecyclerViewAdapter
import de.oth.rpsa.data.RpsaDatabase
import de.oth.rpsa.databinding.CustomerViewBinding
import de.oth.rpsa.model.Customer
import de.oth.rpsa.repository.CustomerRepository
import de.oth.rpsa.viewModel.CustomerViewModel
import de.oth.rpsa.viewModel.CustomerViewModelFactory

class RpsaMainViewModel : AppCompatActivity() {

    private lateinit var binding: CustomerViewBinding
    private lateinit var customerViewModel: CustomerViewModel
    private lateinit var adapter: CustomerRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.customer_view)
        val customerDao = RpsaDatabase.getDatabaseInstance(application).customerDao()
        val customerRepository = CustomerRepository(customerDao)
        val customerFactory = CustomerViewModelFactory(customerRepository)
        customerViewModel =
            ViewModelProvider(this, customerFactory).get(CustomerViewModel::class.java)
        binding.customerViewModel = customerViewModel
        binding.lifecycleOwner = this
        displayCustomerList()
        initRecyclerView()
        switchMode()
        initDropDown()

        customerViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun initRecyclerView() {
        binding.customerRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            CustomerRecyclerViewAdapter({ selectedItem: Customer -> listCustomerClicked(selectedItem) })
        binding.customerRecyclerView.adapter = adapter
        displayCustomerList()

    }

    private fun displayCustomerList() {
        customerViewModel.customer.observe(this, Observer {
            adapter.setCustomersList(it)
            adapter.notifyDataSetChanged()
        })

    }

    private fun listCustomerClicked(customer: Customer) {
        customerViewModel.initUpdateAndDeleteButton(customer)
    }

    private fun switchMode() {
        val switch: Switch = binding.switchMode
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }
        }
    }

    private fun initDropDown() {
        val vehicles = resources.getStringArray(R.array.vehicles)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, vehicles)
        arrayAdapter.notifyDataSetChanged()
        binding.vehicleText.setAdapter(arrayAdapter)

    }
}

