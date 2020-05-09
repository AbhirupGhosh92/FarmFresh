package com.app.farmfresh.fragmets.shop

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.farmfresh.R
import com.app.farmfresh.viewmodels.shop.ShopHomeViewModel

class ShopHome : Fragment() {

    companion object {
        fun newInstance() = ShopHome()
    }

    private lateinit var viewModel: ShopHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shop_home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ShopHomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
