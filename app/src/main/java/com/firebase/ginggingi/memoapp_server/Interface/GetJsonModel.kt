package com.firebase.ginggingi.memoapp_server.Interface

import com.firebase.ginggingi.memoapp_server.MemosActivity

interface GetJsonModel {
    fun InitDatas(url: String, mActivity: MemosActivity)
    fun ChangeURL(url: String)
    fun ParseJsonFromUrl()
    fun ParseJsonFromUrl(datas: String)
    fun GiveJsonArr()
}