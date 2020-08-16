package com.app.farmfresh.fragmets.managers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.farmfresh.R
import com.app.farmfresh.adapters.ManagerDeliveryBoysAdapter
import com.app.farmfresh.databinding.FragmentManageDeliveryBoysBinding
import com.app.farmfresh.databinding.FragmentManagerAddDeliveryBoysBinding
import com.app.farmfresh.models.UserModel
import com.app.farmfresh.viewmodels.managers.ManagerHomeViewModel

class ManagerAddDeliveryBoys : Fragment() {
    private lateinit var dataBinding : FragmentManagerAddDeliveryBoysBinding
    private var userList = mutableListOf<UserModel>()
    private lateinit var viewModel : ManagerHomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding =  DataBindingUtil.inflate(layoutInflater,R.layout.fragment_manager_add_delivery_boys, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dataBinding.rvDeliveryBoys.adapter = ManagerDeliveryBoysAdapter(userList)
        dataBinding.rvDeliveryBoys.layoutManager = LinearLayoutManager(requireContext())
        dataBinding.rvDeliveryBoys.itemAnimator = DefaultItemAnimator()
        dataBinding.rvDeliveryBoys.adapter?.notifyDataSetChanged()

        viewModel = ViewModelProvider(this)[ManagerHomeViewModel::class.java]
        viewModel.getDeliveryBoyList().observe(viewLifecycleOwner, Observer {
            userList.clear()
            userList.addAll(it)
            dataBinding.rvDeliveryBoys.adapter?.notifyDataSetChanged()
        })

    }
}