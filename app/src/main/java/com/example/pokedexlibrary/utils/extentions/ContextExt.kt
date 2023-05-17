package com.example.pokedexlibrary.utils.extentions

import android.content.Context
import android.widget.Toast

fun Context.showToastMsg(str: String?) {
    Toast.makeText(this, str.toString(), Toast.LENGTH_SHORT).show()
}