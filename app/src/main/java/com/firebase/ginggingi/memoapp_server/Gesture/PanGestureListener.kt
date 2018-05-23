package com.firebase.ginggingi.memoapp_server.Gesture

import android.view.GestureDetector
import android.view.MotionEvent

/**
 * Created by GingGingI on 2018-05-20.
 */
class PanGestureListener : GestureDetector.SimpleOnGestureListener() {

    //이후를위해생성 하지만잠시봉인
    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {

        return true
    }

}