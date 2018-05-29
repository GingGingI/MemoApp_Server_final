package com.firebase.ginggingi.memoapp_server.ServerConn

import android.widget.Toast
import com.firebase.ginggingi.memoapp_server.Interface.GetJsonModel
import com.firebase.ginggingi.memoapp_server.MemosActivity

class GetJson: GetJsonModel {
    private val TAG:String = "GETJSON"
    private lateinit var SConn: ServerConnecter
    private lateinit var mActivity: MemosActivity
    protected lateinit var url: String
    var TimeWaited: Double = 0.0

    override fun InitDatas(url: String, mActivity: MemosActivity) {
        this.mActivity = mActivity
        this.url = url
    }

    override fun ChangeURL(url: String) {
        this.url = url
    }

    override fun ParseJsonFromUrl() {
        SConn = ServerConnecter(url)
        onParsing()
    }

    override fun ParseJsonFromUrl(datas: String) {
        SConn = ServerConnecter(url, datas)
        onParsing()
    }

    fun onParsing(){
        GiveJsonArr()
    }

    fun getResult(): String? {
        return SConn.returnDatas()
    }

    override fun GiveJsonArr() {
//        다받아와질때까지 while을돌려서 시간을잼
        while (!SConn.isComplete) {
            Thread.sleep(100)
            TimeWaited += 0.1
        }
        if (SConn.isComplete) {
            if (SConn.isGetError){
                Toast.makeText(mActivity, "Error founded try again", Toast.LENGTH_LONG)
            }else {
                mActivity.CompleteToGetJson()
            }
        }
//        다받아와지면 complete초기화
        SConn.isComplete = false
    }
}