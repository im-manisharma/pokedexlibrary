package com.example.pokedexlibrary.utils

object AppUtils {
    fun getHeightInFoot(heightInDecimeter: Int) : String{
        val inches = heightInDecimeter * 3.937
        val foot = (inches / 12).toInt()
        val inch = (inches % 12).toInt()
        return "$foot'$inch''"
    }

    fun getWeightInKg(hectogram: Int): String{
        val weightInKg = hectogram / 10f
        return String.format("%.1f Kg", weightInKg)
    }

    fun getPokemonGenders(genderRate: Int?): String{
        return when(genderRate) {
            1,2,4,6,7 -> "Male,Female"
            8 -> "Female"
            -1 -> "Genderless"
            else -> ""
        }
    }
}