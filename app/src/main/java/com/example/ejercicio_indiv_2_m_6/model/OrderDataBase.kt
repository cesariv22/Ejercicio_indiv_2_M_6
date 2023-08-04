package com.example.ejercicio_indiv_2_m_6.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Order:: class], version = 1)
abstract class OrderDataBase :RoomDatabase() {

    // conexion Dao con la base de datos
    abstract  fun getOrderDao(): OrderDao

    // COMPANION OBJECT EXPONE UN OBJETO SIN INSTANCIAR LA CLASE
    companion object {
        // ESTA VARIABLE ESTE SIEMPRE DISPONIBLE
        @Volatile
        private var INSTANCE: OrderDataBase? = null
        // MAIN THREAD
        // BACK THREAD HILO SECUNDARIOS VOLATILE HACE QUE SE EJECUTE DONDE ESTE DISPONIBLE
        // TAREAS ASINCRONAS

        // CONTEXTO DONDE ESTAMOS EJECUTANDO LOS PROCESOS
        // MUCHAS FORMAS DE EJECUTAR EL CONTEXTO
        fun getDataBase(context: Context): OrderDataBase {
            val tempInntance = INSTANCE
            // ES DISTINTO A NULL
            if (tempInntance != null) {

                return tempInntance
            }

            // LLAMA A UN METODO Y LO SINCRONIZA PARA INSTANCIAR
            synchronized(this) {
                // CLASE DE ROOM ES EL CREADOR DE LA INSTANCIA DE LA BASE DE DATOS
                val instance = Room.databaseBuilder(
                    // Que la base de datos sea una para toda la app
                    context.applicationContext,
                    // NOMBRE DEL ARCHIVO QUE CONTIENE LA BASE DE DATO
                    OrderDataBase::class.java,
                    "OrderEntity"
                )
                    .build()
                INSTANCE = instance
                return instance

            }
        }
    }
}