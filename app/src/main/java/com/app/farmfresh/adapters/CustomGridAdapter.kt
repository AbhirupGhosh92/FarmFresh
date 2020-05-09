package com.app.farmfresh.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.app.farmfresh.R
import com.app.farmfresh.repo.models.GridLayoutItem


class CustomGridAdapter(var list: List<GridLayoutItem>,var snippet : (name : String) -> Unit) : BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var tempView : View
        if (p1 == null) {
            tempView = LayoutInflater.from(p2?.context).inflate(R.layout.selctor_item_layout,p2,false)
            var imageView = tempView.findViewById<ImageView>(R.id.iv_icon)
            imageView.setImageResource(list[p0].resourceId)
            var text = tempView.findViewById<TextView>(R.id.tv_name)
            text.text = list[p0].name

            tempView.setOnClickListener {
                snippet.invoke(list[p0].name)
            }


        } else {
            tempView = p1
        }
        return tempView
    }

    override fun getItem(p0: Int): Any {
        return Any()
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }
}