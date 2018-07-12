package view.utility

import android.content.Context
import android.net.ConnectivityManager

class Utility {
    companion object {
        fun noNetwork(context: Context): Boolean {
            val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = conMgr.activeNetworkInfo
            return netInfo == null
        }

        var DB_NAME = "character_names"
    }


}