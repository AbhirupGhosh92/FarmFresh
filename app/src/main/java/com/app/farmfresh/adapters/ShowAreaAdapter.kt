package com.app.farmfresh.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.farmfresh.R
import com.app.farmfresh.fragmets.master.EditAreaDialogFragment
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.viewholders.ShowAreaViewHolder

class ShowAreaAdapter(var fragmentManager : NavController, var list : List<AreaModel>) : RecyclerView.Adapter<ShowAreaViewHolder>() {

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

        holder.showAreaItemBinding.root.setOnClickListener {

            var bundle = Bundle().also {
                it.putSerializable("area_model",list[position])
            }

            fragmentManager.navigate(R.id.action_areaPagerFragment_to_editAreaDialogFragment,bundle)

        }

    }
}