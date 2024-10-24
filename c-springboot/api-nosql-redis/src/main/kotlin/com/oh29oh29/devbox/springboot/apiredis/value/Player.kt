package com.oh29oh29.devbox.springboot.apiredis.value

enum class Player {
    Messi,
    Ronaldo,
    Henry,
    Haaland,
    Salah,
    Kane,
    Lewandowski,
    Mbappe,
    Vinicius,
    Son
    ;

    companion object {
        fun of(name: String) = entries.find { it.name == name} ?: throw RuntimeException("Unknown Player.. $name")
    }
}