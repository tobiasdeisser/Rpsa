package de.oth.rpsa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import de.oth.rpsa.R
import de.oth.rpsa.databinding.ListcustomersBinding
import de.oth.rpsa.model.Customer

class CustomerRecyclerViewAdapter(
    private val customerList: List<Customer>,
    private val clickListener: (Customer) -> Unit
) :
    RecyclerView.Adapter<myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListcustomersBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.listcustomers, parent, false)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(customerList[position], clickListener)

    }

    override fun getItemCount(): Int {
        return customerList.size
    }


}

class myViewHolder(val binding: ListcustomersBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(customer: Customer, clickListener: (Customer) -> Unit) {
        binding.lastnameTextview.text = customer.lastName
        binding.firstnameTextview.text = customer.firstName
        binding.telephoneTextview.text = customer.telephone.toString()
        binding.emailTextview.text = customer.eMail

        binding.listCustomerLayout.setOnClickListener {
            clickListener(customer)
        }


    }
}