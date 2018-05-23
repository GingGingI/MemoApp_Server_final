package com.firebase.ginggingi.memoapp_server

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_memos.*

class MemosActivity : AppCompatActivity(), View.OnClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memos)
        setSupportActionBar(toolbar)

        Init()
        ViewInit()
    }

    private fun Init() {

    }

    private fun ViewInit() {
        fab.setOnClickListener(this)
        fab.setImageDrawable(resources.getDrawable(R.drawable.ic_add, theme))

//        SubView.translationY =
    }


    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.fab -> {

            }
        }
    }
}
