package com.app.farmfresh.fragmets.master


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.farmfresh.R
import com.app.farmfresh.databinding.FragmentAreaDetailsBinding
import com.app.farmfresh.repo.models.AreaModel


/**
 * A simple [Fragment] subclass.
 */
class AreaDetailsFragment : Fragment() {

    private lateinit var dataBinding : FragmentAreaDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_area_details, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var areaModel = arguments?.getSerializable("area_model") as AreaModel

    }


}
