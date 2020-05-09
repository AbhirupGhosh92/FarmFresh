package com.app.farmfresh.fragmets.deliveryboys

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.farmfresh.R
import com.app.farmfresh.viewmodels.deliveryboys.DeliveryBoysOrderItemsViewModel

class DeliveryBoysOrderItems : Fragment() {

    companion object {
        fun newInstance() = DeliveryBoysOrderItems()
    }

    private lateinit var viewModel: DeliveryBoysOrderItemsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.delivery_boys_order_items_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DeliveryBoysOrderItemsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
