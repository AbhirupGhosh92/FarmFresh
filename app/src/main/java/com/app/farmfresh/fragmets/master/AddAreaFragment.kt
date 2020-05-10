package com.app.farmfresh.fragmets.master

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.app.farmfresh.R
import com.app.farmfresh.databinding.AreaFragmenntFragmentBinding
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.viewmodels.master.AreaFragmentViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AddAreaFragment : Fragment() {

    private lateinit var viewModel: AreaFragmentViewModel
    private lateinit var databinding : AreaFragmenntFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        databinding = DataBindingUtil.inflate(inflater,R.layout.area_fragmennt_fragment, container, false)

        return databinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        databinding.btnSave.setOnClickListener {

            if(databinding.edtAreaName.text.isNullOrEmpty().not() && databinding.edtDellCharge.text.isNullOrEmpty() && databinding.edtMinBill.text.isNullOrEmpty().not())
            viewModel.addArea(AreaModel(
                databinding.edtAreaName.text.toString(),
                databinding.edtMinBill.text.toString().toFloat(),
                databinding.edtDellCharge.text.toString().toFloat(),
                databinding.edtManagerName.text.toString()
            ))
            else
                Toast.makeText(requireContext(),resources.getString(R.string.enter_valid_feilds),Toast.LENGTH_SHORT).show()
        }

    }

}
