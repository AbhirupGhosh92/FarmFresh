package com.app.farmfresh.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.farmfresh.R
import com.app.farmfresh.databinding.HolderManagerAddDeliverBinding
import com.app.farmfresh.models.UserModel

class ManagerDeliveryBoysAdapter(var list: List<UserModel>) : RecyclerView.Adapter<ManagerDeliveryBoysAdapter.ViewHolder>() {

    data class ViewHolder(var dataBinding : HolderManagerAddDeliverBinding) : RecyclerView.ViewHolder(dataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.holder_manager_add_deliver,parent,false))
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBinding.tvDeliveryName.text = list[position].displayName
    }

}