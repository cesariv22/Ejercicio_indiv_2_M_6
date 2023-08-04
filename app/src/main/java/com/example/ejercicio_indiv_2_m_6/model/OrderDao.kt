package com.example.ejercicio_indiv_2_m_6.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface OrderDao {

    // Insertar un pedido tiene una estrategia si se repite el Id
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)

    //Actualizar un pedido
    @Update
    suspend  fun updateOrder(order: Order)

    //Borrar un pedido
    @Delete
    suspend  fun deleteOneOrder(order: Order)

    //Borrar todos los pedidos
    @Query("DELETE FROM REGISTER_TABLE")
    suspend fun deleteAll()

    //Traer todos los pedidos
    @Query("SELECT * FROM REGISTER_TABLE ORDER BY id ASC")
    fun getAllOrder(): LiveData<List<Order>>

}