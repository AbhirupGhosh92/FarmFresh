package com.app.farmfresh.fragmets.master

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.farmfresh.R
import com.app.farmfresh.viewmodels.master.AreaFragmentViewModel

class AreaFragment : Fragment() {

    private lateinit var viewModel: AreaFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.area_fragmennt_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AreaFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
