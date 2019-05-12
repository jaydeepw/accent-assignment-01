package com.github.jaydeepw.assignment01

import android.content.Context
import android.net.ConnectivityManager

class Utils {

    object Companion {

        /**
         * Checks if the Internet connection is available.
         *
         * @return Returns true if the Internet connection is available. False otherwise.
         */
        fun isInternetAvailable(ctx: Context): Boolean {
            val connectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkInfo = connectivityManager.activeNetworkInfo

            // if network is NOT available networkInfo will be null
            // otherwise check if we are connected
            return if (networkInfo != null && networkInfo.isConnected) {
                true
            } else false

        }

    }
}