package com.app.farmfresh.fragmets.master

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.farmfresh.R
import com.app.farmfresh.viewmodels.master.MasterHomeViewModel

class MasterHome : Fragment() {

    companion object {
        fun newInstance() = MasterHome()
    }

    private lateinit var viewModel: MasterHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.master_home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MasterHomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
