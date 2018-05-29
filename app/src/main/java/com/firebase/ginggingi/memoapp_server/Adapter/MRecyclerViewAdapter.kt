package com.firebase.ginggingi.memoapp_server.Adapter

import android.content.Context
import android.opengl.Visibility
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.firebase.ginggingi.memoapp_server.DataModels.MemoDataModel
import com.firebase.ginggingi.memoapp_server.MemosActivity
import com.firebase.ginggingi.memoapp_server.R
import kotlinx.android.synthetic.main.activity_memos.*

/**
 * Created by GingGingI on 2018-05-19.
 */
class MRecyclerViewAdapter: RecyclerView.Adapter<ViewHolder>(){

    private var listViewItemList: ArrayList<MemoDataModel> = ArrayList()
    lateinit var mActivity: MemosActivity

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        mActivity = parent?.context as MemosActivity
        val v: View = LayoutInflater.from(parent?.context as Context).inflate(R.layout.listitem_miniview, parent, false)
        val vh = ViewHolder(v)
        return vh
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Title.setText(listViewItemList.get(position).getTitle())
        holder.Time.setText(listViewItemList.get(position).getTime())
        holder.itemView.setOnClickListener(View.OnClickListener {
            mActivity.SubView_Title.setText(listViewItemList.get(position).getTitle())
            mActivity.SubView_Time.setText(listViewItemList.get(position).getTime())
            mActivity.SubView_Content.setText(listViewItemList.get(position).getContent())
        })
    }

    override fun getItemCount(): Int {
        return listViewItemList.size
    }

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val Title: TextView = itemView.findViewById(R.id.List_TitleView)
    val Time: TextView = itemView.findViewById(R.id.List_TimeView)
}