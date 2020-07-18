package com.app.farmfresh.fragmets.master

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.farmfresh.R
import com.app.farmfresh.adapters.ShowAreaAdapter
import com.app.farmfresh.databinding.FragmentShowAreasBinding
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.viewmodels.master.AreaFragmentViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShowAreasFragment : Fragment() {

    private var areaList = mutableListOf<AreaModel>()
    private lateinit var dataBinding : FragmentShowAreasBinding
    private lateinit var viewModel : AreaFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_show_areas,container,false)

        dataBinding.rvAreaData.adapter = ShowAreaAdapter(requireActivity().supportFragmentManager,areaList)
        dataBinding.rvAreaData.itemAnimator = DefaultItemAnimator()
        dataBinding.rvAreaData.layoutManager = LinearLayoutManager(requireContext())
        dataBinding.rvAreaData.adapter?.notifyDataSetChanged()

        return dataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AreaFragmentViewModel::class.java)


        viewModel.getAreaList()?.observe(viewLifecycleOwner, Observer {

            if(it.isNullOrEmpty().not()) {

                dataBinding.rvAreaData.visibility = View.VISIBLE
                dataBinding.tvNoData.visibility = View.GONE

                areaList.clear()
                areaList.addAll(it)
                dataBinding.rvAreaData.adapter?.notifyDataSetChanged()
            }
            else
            {
                dataBinding.rvAreaData.visibility = View.GONE
                dataBinding.tvNoData.visibility = View.VISIBLE
            }
        })
    }
}
