package com.example.ejercicio_indiv_2_m_6.model


import androidx.room.Entity
import androidx.room.PrimaryKey

//Representa la tabla de la base de datos
@Entity(tableName = "register_table")

data class Order (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nameItem: String,
    val priceUnit: Int,
    val amount: Int,
    val totalPrice: Int,
)