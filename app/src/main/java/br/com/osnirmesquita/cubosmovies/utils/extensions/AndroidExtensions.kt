package br.com.osnirmesquita.cubosmovies.utils.extensions

import android.content.Context
import android.widget.Toast

fun Context.displayToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}