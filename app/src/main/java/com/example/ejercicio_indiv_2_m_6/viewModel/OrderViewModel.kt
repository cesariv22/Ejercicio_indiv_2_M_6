package com.example.ejercicio_indiv_2_m_6.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ejercicio_indiv_2_m_6.model.Order
import com.example.ejercicio_indiv_2_m_6.model.OrderDataBase
import com.example.ejercicio_indiv_2_m_6.repository.OrderRepository
import kotlinx.coroutines.launch

class OrderViewModel (application: Application): AndroidViewModel(application) {

    // conexión con el repositorio
    private val repository : OrderRepository

    // LIVE DATA QUE EXPONE LA INFO DEL MODELO
    val allOrder : LiveData<List<Order>>

    init{
        // obteniendo instancia del dao
        val orderDao = OrderDataBase.getDataBase(application).getOrderDao()
        repository = OrderRepository(orderDao)
        allOrder = repository.listAllOrder
    }

    fun insertOrder(order: Order)= viewModelScope.launch {
        repository.insertOrder(order)
    }

    fun updateOrder(order: Order)= viewModelScope.launch {
        repository.updateOrder(order)
    }

    fun deleteOneOrder(order: Order)= viewModelScope.launch {
        repository.deleteOneOrder(order)
    }

    fun deleteAll()=viewModelScope.launch {
        repository.deleteAll()
    }

    // Para seleccionar algun Elemento
    private val selectedOrder : MutableLiveData<Order?> = MutableLiveData()



    // funcion para recibir un pedido seleccionado desde el Rv
    fun selected(order: Order?){
        // guarda lo que estamos seleccionando
        selectedOrder.value= order
    }


    // para mostrar los elementos luego de una seleccion Recibir  el objeto seleccionado
    fun selectedItem(): LiveData<Order?> = selectedOrder

}