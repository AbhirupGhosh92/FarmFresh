package com.app.farmfresh.fragmets.master

import android.os.Bundle
import android.util.Patterns
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
import com.app.farmfresh.adapters.UserListAdapter
import com.app.farmfresh.constants.Constants
import com.app.farmfresh.databinding.FragmentAddManagerFragmetBinding
import com.app.farmfresh.models.GrantAccessModel
import com.app.farmfresh.models.AddUserDetailsModel
import com.app.farmfresh.models.GetUserDetailModel
import com.app.farmfresh.viewmodels.master.EditManagerViewModel
import com.google.android.material.snackbar.Snackbar


class AddManagerFragmet : Fragment() {

    private lateinit var dataBindinng : FragmentAddManagerFragmetBinding
    private var managerList = arrayListOf<GetUserDetailModel>()
    private lateinit var viewModel : EditManagerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBindinng = DataBindingUtil.inflate(inflater, R.layout.fragment_add_manager_fragmet, container, false)
        return dataBindinng.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this)[EditManagerViewModel::class.java]

        dataBindinng.button.setOnClickListener {

            if(dataBindinng.edtAddManager.text.isNullOrEmpty().not() && Patterns.EMAIL_ADDRESS.matcher(dataBindinng.edtAddManager.text.toString()).matches())
            {
                viewModel.grantAccess(GrantAccessModel(
                    dataBindinng.edtAddManager.text.toString(),Constants.manager
                )).observe(viewLifecycleOwner, Observer {
                    Snackbar.make(dataBindinng.root,it.responseMessage,Snackbar.LENGTH_SHORT).show()
                })
            }
            else
            {
                Snackbar.make(dataBindinng.root,"Please enter a valid email address",Snackbar.LENGTH_SHORT).show()
            }

        }

        dataBindinng.rvManagerList.adapter = UserListAdapter(managerList)
        dataBindinng.rvManagerList.layoutManager = LinearLayoutManager(requireContext())
        dataBindinng.rvManagerList.itemAnimator = DefaultItemAnimator()
        dataBindinng.rvManagerList.adapter?.notifyDataSetChanged()

        viewModel.getManagerList().observe(viewLifecycleOwner, Observer {
            managerList.clear()
            managerList.addAll(it)
            dataBindinng.rvManagerList.adapter?.notifyDataSetChanged()
        })

    }
}