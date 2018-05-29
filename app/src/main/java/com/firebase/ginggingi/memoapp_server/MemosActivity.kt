package com.firebase.ginggingi.memoapp_server

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import com.firebase.ginggingi.memoapp_server.Adapter.MRecyclerViewAdapter
import com.firebase.ginggingi.memoapp_server.DataModels.MemoDataModel
import kotlinx.android.synthetic.main.activity_memos.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MemosActivity : BaseActivity(), View.OnClickListener, View.OnTouchListener{

    var JArr : JSONArray? = null

    var MDModelList : ArrayList<MemoDataModel> = ArrayList()
    var MDModel : MemoDataModel? = null

    lateinit var lm: RecyclerView.LayoutManager
    lateinit var adapter : MRecyclerViewAdapter

    val metrics = DisplayMetrics()
    var layoutMarginX = 0
    var slideActive = false
    var dp = 0

    val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memos)
        setSupportActionBar(toolbar)

        Init()
        ViewInit()
//        getJsons()
    }

    private fun Init() {
        this.windowManager.defaultDisplay.getMetrics(metrics)
        params.leftMargin = getDp(100)
        SubView.layoutParams = params

        dp = resources.getDimensionPixelSize(R.dimen.Size1dp)
        Log.i("MemoActivity", dp.toString())

        adapter = MRecyclerViewAdapter()
        lm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun ViewInit() {
        fab.setOnClickListener(this)
        fab.setImageDrawable(resources.getDrawable(R.drawable.ic_add, theme))

        RecyclerView.layoutManager = lm
        RecyclerView.adapter = adapter
        SubView.setOnTouchListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.fab -> {
                //goto MemoAddActivitiy
                //임시
                params.leftMargin = getDp(20)
                layoutMarginX = getDp(20)
                slideActive = true
                SubView.layoutParams = params
            }
        }
    }

    private fun getJsons() {
        //값이있을경우 제거함
        if (JArr != null) {JArr = JSONArray()}
        if (MDModelList != null) {MDModelList = ArrayList()}
        GJson.InitDatas(URLMaker(0), this)
        GJson.ParseJsonFromUrl()
    }

    fun CompleteToGetJson(){
        makeJson(GJson.getResult())

        for(i in 0..(JArr!!.length() - 1)) {
            MDModel = MemoDataModel()
            try{
                val idx = JArr!!.getJSONObject(i).getInt("idx")
                val time = JArr!!.getJSONObject(i).getString("Time")
                val title = JArr!!.getJSONObject(i).getString("Title")
                val content = JArr!!.getJSONObject(i).getString("Content")

                MDModel!!.setDatas(idx,time,title,content)
                MDModelList.add(MDModel!!)
            }catch (e: JSONException){
                e.printStackTrace()
            }
        }
    }

    private fun makeJson(result: String?){
        val JObject = JSONObject(result)
        JArr = JObject.getJSONArray(getString(R.string.JsonName))
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN && slideActive) {
            var VMValue = 1 // View Margin Value
            Log.i("TouchListener", "${event?.x} , ${event?.y}")

            val thread = Thread(Runnable {
                layoutMarginX += VMValue
                params.leftMargin = layoutMarginX
            })

            while (layoutMarginX < metrics.widthPixels) {
                thread.run()
                this.runOnUiThread {
                    SubView.layoutParams = params
                    SubView.requestLayout()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun getDp(value: Int): Int {
        return value * dp
    }
}
