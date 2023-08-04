package com.example.ejercicio_indiv_2_m_6.viewUI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio_indiv_2_m_6.R
import com.example.ejercicio_indiv_2_m_6.databinding.FragmentSecondBinding
import com.example.ejercicio_indiv_2_m_6.viewModel.OrderViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var orderAdapter: AdapterItem
    private val viewModel: OrderViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.allOrder.observe(viewLifecycleOwner) { orders ->
            orderAdapter.itemList = orders.toMutableList()
            orderAdapter.notifyDataSetChanged()

            val totalAmount = orders.sumOf { it.totalPrice }
            binding.txvTotalValue.text = totalAmount.toString()
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }

        binding.buttonDeleteList.setOnClickListener {
            viewModel.deleteAll()
        }
    }

    private fun setupRecyclerView() {
        orderAdapter = AdapterItem(emptyList())
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = orderAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}