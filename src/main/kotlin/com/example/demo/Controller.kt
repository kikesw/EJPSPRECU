package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.security.MessageDigest
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


private fun cifrar(textoEnString: String, llaveEnString: String): String {
    println("Voy a cifrar $textoEnString")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, getKey(llaveEnString))
    val textCifrado = Base64.getEncoder().encodeToString(cipher.doFinal(textoEnString.toByteArray(Charsets.UTF_8)))
    println("He obtenido $textCifrado")
    return textCifrado
}

@Throws(BadPaddingException::class)
private fun descifrar(textoCifrrado: String, llaveEnString: String): String {
    println("Voy a descifrar $textoCifrrado")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.DECRYPT_MODE, getKey(llaveEnString));
    val textDescifrado = String(cipher.doFinal(Base64.getDecoder().decode(textoCifrrado)))
    println("He obtenido $textDescifrado")
    return textDescifrado
}


private fun getKey(llaveEnString: String): SecretKeySpec {
    var llaveUtf8 = llaveEnString.toByteArray(Charsets.UTF_8)
    val sha = MessageDigest.getInstance("SHA-1")
    llaveUtf8 = sha.digest(llaveUtf8)
    llaveUtf8 = llaveUtf8.copyOf(16)
    return SecretKeySpec(llaveUtf8, "AES")
}



@RestController
class Controller (private val repositorio:Repositorio){

    @GetMapping("/doc/nombre")
    fun getNombre():List<Usuario>{
        return repositorio.findAll()
    }


    /*@GetMapping("/documentos/{n1}/{n2}")
    fun sumaNumeros(@PathVariable n1 : Int, @PathVariable n2 : Int) : Int {
        Database.logger.info("Sumando... $n1, $n2")
        return n1 + n2
    }*/
    @GetMapping("/doc")
    fun getUsuarios(): List<Usuario> {

        return repositorio.findAll()
    }

    @GetMapping("/doc/{id}")
    fun getCountry(@PathVariable id: Long): Usuario {
        val encriptacion = "hola"
        val tipodellave = "QWERTY"
        val descifrada = descifrar(cifrar(encriptacion,tipodellave),tipodellave)
        return repositorio.findById(descifrada.toLong()).get()

    }


}