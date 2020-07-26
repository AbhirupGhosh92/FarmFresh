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
import androidx.lifecycle.ViewModelProvider
import com.app.farmfresh.R
import com.app.farmfresh.databinding.AreaFragmenntFragmentBinding
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.viewmodels.master.AreaFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AddAreaFragment : Fragment() {

   lateinit var viewModel: AreaFragmentViewModel
    private lateinit var databinding : AreaFragmenntFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        databinding = DataBindingUtil.inflate(inflater,R.layout.area_fragmennt_fragment, container, false)

        viewModel = ViewModelProvider(this).get(AreaFragmentViewModel::class.java)

        return databinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        databinding.btnSave.setOnClickListener {

            if(databinding.edtAreaName.text.isNullOrEmpty().not() && databinding.edtDellCharge.text.isNullOrEmpty().not() && databinding.edtMinBill.text.isNullOrEmpty().not()) {
                viewModel.addArea(
                    AreaModel(
                        databinding.edtAreaName.text.toString(),
                        databinding.edtMinBill.text.toString().toFloat(),
                        databinding.edtDellCharge.text.toString().toFloat(),
                        databinding.edtAreaId.text.toString()
                    )
                ).observe(viewLifecycleOwner, Observer {
                    Snackbar.make(databinding.root,it.data,Snackbar.LENGTH_SHORT).show()
                })
            }
            else
                Snackbar.make(databinding.root,resources.getString(R.string.enter_valid_feilds),Snackbar.LENGTH_SHORT).show()
        }

    }

}
