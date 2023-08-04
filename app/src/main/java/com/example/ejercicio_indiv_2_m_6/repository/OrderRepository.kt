package com.example.ejercicio_indiv_2_m_6.repository

import androidx.lifecycle.LiveData
import com.example.ejercicio_indiv_2_m_6.model.Order
import com.example.ejercicio_indiv_2_m_6.model.OrderDao

// Responsabilidad exponer los datos para el ViewModel

class OrderRepository(private val orderDao: OrderDao) {
    // Este value va a contener toda la información de la BD todos los pedidos
    val listAllOrder: LiveData<List<Order>> = orderDao.getAllOrder()

    suspend fun insertOrder(order: Order) {
        orderDao.insertOrder(order)
    }

    suspend fun updateOrder(order: Order) {
        orderDao.updateOrder(order)
    }

    suspend fun deleteOneOrder(order: Order) {
        orderDao.deleteOneOrder(order)
    }

    suspend fun deleteAll() {
        orderDao.deleteAll()
    }
}