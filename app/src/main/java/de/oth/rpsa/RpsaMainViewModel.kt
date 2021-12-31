package de.oth.rpsa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import de.oth.rpsa.R
import de.oth.rpsa.adapter.CustomerRecyclerViewAdapter
import de.oth.rpsa.data.RpsaDatabase
import de.oth.rpsa.databinding.ActivityMainBinding
import de.oth.rpsa.model.Customer
import de.oth.rpsa.repository.CustomerRepository
import de.oth.rpsa.viewModel.CustomerViewModel
import de.oth.rpsa.viewModel.CustomerViewModelFactory

class RpsaMainViewModel : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var customerViewModel: CustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val customerDao = RpsaDatabase.getDatabaseInstance(application).customerDao()
        val customerRepository = CustomerRepository(customerDao)
        val customerFactory = CustomerViewModelFactory(customerRepository)
        customerViewModel =
            ViewModelProvider(this, customerFactory).get(CustomerViewModel::class.java)
        binding.customerViewModel = customerViewModel
        binding.lifecycleOwner = this
        displayCustomerList()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.customerRecyclerView.layoutManager = LinearLayoutManager(this)
        displayCustomerList()

    }

    private fun displayCustomerList() {
        customerViewModel.customer.observe(this, Observer {
            Log.i("MyTag", it.toString())
            binding.customerRecyclerView.adapter = CustomerRecyclerViewAdapter(it,{selectedItem:Customer-> listCustomerClicked(selectedItem)})
        })

    }

    private fun listCustomerClicked(customer: Customer){
        Toast.makeText(this,customer.lastName, Toast.LENGTH_LONG).show()
    }

}