package com.dizcoding.core.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle

fun Activity.intentTo(c: Class<*>, vararg bundle: Bundle) {
    val intent = Intent(this, c).apply {
        if (bundle.isNotEmpty()) {
            bundle.forEach {
                putExtras(it)
            }
        }
    }
    startActivity(intent)
}
fun Activity.intentFinishTo(c: Class<*>, vararg bundle: Bundle) {
    val intent = Intent(this, c).apply {
        if (bundle.isNotEmpty()) {
            bundle.forEach {
                putExtras(it)
            }
        }
    }
    startActivity(intent)
    finish()
}