package com.app.farmfresh.fragmets.master

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.farmfresh.R
import com.app.farmfresh.databinding.FragmentShowAreasBinding

class ShowAreasFragment : Fragment() {

    private lateinit var dataBinding : FragmentShowAreasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_show_areas,container,false)



        return dataBinding.root
    }
}
