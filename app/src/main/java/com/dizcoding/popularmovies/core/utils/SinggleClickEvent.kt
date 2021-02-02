package com.dizcoding.popularmovies.core.utils

import android.os.Handler
import android.view.View

interface SinggleClickEvent: View.OnClickListener {
    companion object{
        private var TIME = 1 * 1000.toLong()
    }

    override fun onClick(v: View?) {
        v?.isEnabled = false
        Handler().postDelayed({ v!!.isEnabled = true }, TIME)
    }
}