package com.app.farmfresh.fragmets.generic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.farmfresh.R
import com.app.farmfresh.databinding.FragmentProfileBinding
import com.app.farmfresh.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var dataBinding : FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.getCurrentProfileData().observe(viewLifecycleOwner, Observer {
            var temp = it
        })

    }
}