package com.firebase.ginggingi.memoapp_server.ServerConn

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class ServerConnecter {
    private var ServerTask: ServerConnTask

    private var url: String
    private var datas: String? = null
    private var result: String? = null
    var isComplete = false
    var isGetError = false

    init {
        ServerTask = ServerConnTask()
    }

    constructor(url: String) {
        this.url = url
        getDatas()
    }

    constructor(url: String, datas: String){
        this.url = url
        this.datas = datas
        getDatas()
    }

    fun getDatas() {
        ServerTask.setDatas(this, url, datas)
        ServerTask.execute()
    }

    fun returnDatas(): String? {
        return result
    }

    private class ServerConnTask: AsyncTask<Void, Void, String>() {

        var isWorking = false

        lateinit var url: String
        lateinit var SConn: ServerConnecter
        var datas: String? = null

        var br: BufferedReader? = null
        var urlConn: HttpURLConnection? = null

        fun setDatas(SConn: ServerConnecter, url: String, datas: String?) {
            this.SConn = SConn
            this.url = url
            this.datas = datas
        }

        override fun onPreExecute() {
            super.onPreExecute()
            isWorking = true
        }

        override fun doInBackground(vararg params: Void?): String? {
            var result: String? = null
            try {
                openConnecter()

                result = getJson()
            }catch (e: MalformedURLException) {
                SConn.isGetError = true
                e.printStackTrace()
            }catch (e: IOException) {
                SConn.isGetError = true
                e.printStackTrace()
            }finally {
                closeConnecter()
                return result
            }
        }

        fun openConnecter() {
            val HttpURL = URL(url)
            urlConn = HttpURL.openConnection() as HttpURLConnection
        }

        fun closeConnecter() {
            try {
                if (br != null)
                    br?.close()
                if (urlConn != null)
                    urlConn?.disconnect()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        fun getJson(): String? {
            if (!datas.equals(null)) {
                PostData(datas)
            }
            val sb = StringBuffer()
            br = BufferedReader(InputStreamReader(urlConn?.inputStream))

            var lines: String? = null
            while ((lines == br!!.readLine()) != null) {
                if (lines != "")
                    sb.append(lines)
            }
            return sb.toString()
        }

        fun PostData(datas: String?) {
            urlConn?.doOutput
//            서버에 값을보내줄준비
            val Os = OutputStreamWriter(urlConn?.outputStream)
//            보냄
            Os.write(datas)
//            Outputsream 비우기
            Os.flush()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            SConn.result = result
            SConn.isComplete = true
            isWorking = false
        }
    }
}