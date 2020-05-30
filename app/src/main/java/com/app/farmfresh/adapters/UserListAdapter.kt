package com.app.farmfresh.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.farmfresh.R
import com.app.farmfresh.databinding.UserListItemLayoutBinding
import com.app.farmfresh.models.UserDetailsModel

class UserListAdapter(var list : List<UserDetailsModel>) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    data class ViewHolder(var dataBinding : UserListItemLayoutBinding) : RecyclerView.ViewHolder(dataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_list_item_layout,parent,false))
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.dataBinding.tvUserList.text = list[position].email
    }

}