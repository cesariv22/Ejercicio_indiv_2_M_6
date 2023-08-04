package com.example.ejercicio_indiv_2_m_6.viewUI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ejercicio_indiv_2_m_6.R
import com.example.ejercicio_indiv_2_m_6.databinding.FragmentFirstBinding
import com.example.ejercicio_indiv_2_m_6.model.Order
import com.example.ejercicio_indiv_2_m_6.viewModel.OrderViewModel


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private var selectedNumber: Int = 1
    private var enteredPrice: Int = 0
    private val viewModel: OrderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numbers = resources.getStringArray(R.array.numbers)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, numbers)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.numberSpinner.adapter = adapter

        binding.numberSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedNumber = parent?.getItemAtPosition(position).toString().toInt()
                updateTotal()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.buttonTotal.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        binding.etAddPrice.addTextChangedListener {
            enteredPrice = it?.toString()?.toIntOrNull() ?: 0
            updateTotal()
        }
        binding.buttonSave.setOnClickListener {
            saveOrder()
        }
    }

    private fun updateTotal() {
        val total = enteredPrice * selectedNumber
        binding.etTotal.setText(total.toString())
    }

    private fun saveOrder() {
        val nameItem = binding.etAddProduct.text.toString().trim()
        val priceUnit = enteredPrice
        val amount = selectedNumber
        val totalPrice = enteredPrice * selectedNumber
        if (nameItem.isNotEmpty()) {
            val order = Order(nameItem = nameItem, priceUnit = priceUnit, amount = amount, totalPrice = totalPrice)
            viewModel.insertOrder(order)
        }
        binding.etAddProduct.text.clear()
        binding.etAddPrice.text.clear()
        binding.etTotal.text.clear()
        binding.numberSpinner.setSelection(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}