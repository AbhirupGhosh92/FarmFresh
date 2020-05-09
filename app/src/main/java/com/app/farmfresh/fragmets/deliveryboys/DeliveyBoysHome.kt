package com.app.farmfresh.fragmets.deliveryboys

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.farmfresh.R
import com.app.farmfresh.viewmodels.deliveryboys.DeliveyBoysHomeViewModel

class DeliveyBoysHome : Fragment() {

    companion object {
        fun newInstance() = DeliveyBoysHome()
    }

    private lateinit var viewModel: DeliveyBoysHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.delivey_boys_home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DeliveyBoysHomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
