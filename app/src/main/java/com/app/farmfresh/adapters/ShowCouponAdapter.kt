package com.app.farmfresh.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.app.farmfresh.R
import com.app.farmfresh.databinding.ShowAreaItemBinding
import com.app.farmfresh.fragmets.master.EditAreaDialogFragment
import com.app.farmfresh.repo.models.CouponModel
import com.app.farmfresh.viewholders.ShowAreaViewHolder

class ShowCouponAdapter(var fragmentManager : NavController,var list : List<CouponModel>) : RecyclerView.Adapter<ShowCouponAdapter.ViewHolder>() {

    private var dialog = EditAreaDialogFragment()

    data class ViewHolder(var showAreaItemBinding : ShowAreaItemBinding) : RecyclerView.ViewHolder(showAreaItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.show_area_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showAreaItemBinding.tvName.text = list[position].code
        holder.showAreaItemBinding.tvMinimumBill.text = list[position].amount.toString()
        holder.showAreaItemBinding.tvDeliveryCharge.text = list[position].desc

        holder.showAreaItemBinding.root.setOnClickListener {

            var bundle = Bundle().also {
                it.putSerializable("coupon_model",list[position])
            }

            fragmentManager.navigate(R.id.action_areaPagerFragment_to_editAreaDialogFragment,bundle)

        }

    }
}