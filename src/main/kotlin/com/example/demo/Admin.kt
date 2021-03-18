package com.example.demo

import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Admin(
        var nombre: String,
        var contrasena: String,
        var claveAD: String) {

    @Id
    @GeneratedValue
    private val id: Long? = null

    override fun equals(other: Any?): Boolean {
        if (other is Usuario){
            return other.id == id && id != null
        } else {
            return false
        }

    }

    override fun hashCode(): Int {
        return Objects.hash(id,nombre,contrasena,claveAD)
    }

    override fun toString(): String {
        return "$nombre tiene la contraseña: $contrasena "
    }



}
/*
La peticion para recibir claves el administrador creo que seria algo asi

fun RecibirClaves():String{
    return Controller.cifrar(texto.findAll().toString(),claveAD)
}

*/
