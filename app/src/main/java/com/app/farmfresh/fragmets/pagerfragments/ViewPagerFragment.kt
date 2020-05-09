package com.app.farmfresh.fragmets.pagerfragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.farmfresh.R

class ViewPagerFragment : Fragment() {

    companion object {
        fun newInstance() = ViewPagerFragment()
    }

    private lateinit var viewModel: ViewPagerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_pager_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ViewPagerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
