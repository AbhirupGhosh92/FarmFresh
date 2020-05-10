package com.app.farmfresh.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.app.farmfresh.R
import com.app.farmfresh.fragmets.master.EditAreaDialogFragment
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.viewholders.ShowAreaViewHolder

class ShowAreaAdapter(var fragmentManager : FragmentManager, var list : List<AreaModel>) : RecyclerView.Adapter<ShowAreaViewHolder>() {

    private var dialog = EditAreaDialogFragment()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowAreaViewHolder {
        return ShowAreaViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.show_area_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ShowAreaViewHolder, position: Int) {
        holder.showAreaItemBinding.tvName.text = list[position].name
        holder.showAreaItemBinding.tvMinimumBill.text = list[position].minimumBill.toString()
        holder.showAreaItemBinding.tvDeliveryCharge.text = list[position].deliveryCharge.toString()
        holder.showAreaItemBinding.tvManagerName.text = list[position].manager

        holder.showAreaItemBinding.root.setOnClickListener {

            dialog.arguments?.putSerializable("area_model",list[position])
            dialog.show(fragmentManager,"edit_area")

        }

    }
}