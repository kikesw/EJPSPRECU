package com.example.demo

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Usuario(
    var nombre : String,
    var contraseña : Int,
    var clave: String,){

    @Id
    @GeneratedValue
    val id: Long? = null

    override fun equals(other: Any?): Boolean {
        if (other is Usuario){
            return other.id == id && id != null
        } else {
            return false
        }

    }

    override fun hashCode(): Int {

        return Objects.hash(id,nombre,contraseña,clave)
    }

    override fun toString(): String {
        return "$id es el id de : $nombre "
    }
}