package com.example.ejercicio_indiv_2_m_6.viewUI

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio_indiv_2_m_6.databinding.ItemRegisterBinding
import com.example.ejercicio_indiv_2_m_6.model.Order


class AdapterItem(var itemList: List<Order>) :
    RecyclerView.Adapter<AdapterItem.OrderViewHolder>() {


    class OrderViewHolder(binding: ItemRegisterBinding) : RecyclerView.ViewHolder(binding.root) {
        val productTextView: TextView = binding.product
        val priceTextView: TextView = binding.price
        val amountTextView: TextView = binding.amount
        val totalTextView: TextView = binding.total
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding =
            ItemRegisterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.productTextView.text = currentItem.nameItem
        holder.priceTextView.text = currentItem.priceUnit.toString()
        holder.amountTextView.text = currentItem.amount.toString()
        holder.totalTextView.text = currentItem.totalPrice.toString()
        }


    override fun getItemCount() = itemList.size

}
