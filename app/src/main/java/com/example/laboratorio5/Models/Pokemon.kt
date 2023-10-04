package com.example.laboratorio5.Models

class Pokemon {
    var name: String? = null
        private set

    var url: String? = null
        private set

    var number: Int? = null
        private set

    var numeroPrueba = 25
        private set

    fun dameNumero(): Int? {
        val urlPartes = url?.split("/")
        val tam = urlPartes?.size
        return urlPartes?.get(urlPartes.size - 2)?.toIntOrNull() ?: 0
    }
}
