package com.example.pokedexlibrary.utils.extentions

import android.content.res.Resources

fun Int.toPx(): Float = this * (Resources.getSystem().displayMetrics.density + 0.5f)