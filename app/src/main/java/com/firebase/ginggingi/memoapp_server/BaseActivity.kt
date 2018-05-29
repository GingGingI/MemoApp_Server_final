package com.firebase.ginggingi.memoapp_server

import android.support.v7.app.AppCompatActivity
import com.firebase.ginggingi.memoapp_server.ServerConn.GetJson

open class BaseActivity: AppCompatActivity() {
    val GJson = GetJson()

    fun URLMaker(Type: Int): String {
        var url: String = ""
        when (Type) {
            0 -> {
                url = getString(R.string.URL_Base)
            }
            1 -> {
                url = getString(R.string.URL_Base) + getString(R.string.URL_ToSend)
            }
            2 -> {
                url = getString(R.string.URL_Base) + getString(R.string.URL_ToUpdate)
            }
            3 -> {
                url = getString(R.string.URL_Base) + getString(R.string.URL_ToDelete)
            }
        }
        return url
    }
}