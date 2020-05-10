package com.app.farmfresh.fragmets.master

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.app.farmfresh.R
import com.app.farmfresh.databinding.FragmentEditAreaDialogBinding
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.viewmodels.master.AreaFragmentViewModel


class EditAreaDialogFragment : DialogFragment() {
   
    private lateinit var dataBinding : FragmentEditAreaDialogBinding
    private lateinit var viewModel : AreaFragmentViewModel
    private  var areaModel : AreaModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        areaModel = arguments?.getSerializable("area_model") as AreaModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_edit_area_dialog,container,false)
        
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(areaModel!=null)
        {
            dataBinding.edtAreaName.setText(areaModel?.name)
            dataBinding.edtMinBill.setText(areaModel?.minimumBill.toString())
            dataBinding.edtDellCharge.setText(areaModel?.deliveryCharge.toString())
            dataBinding.edtManagerName.setText(areaModel?.manager)
        }


        dataBinding.btnEdit.setOnClickListener {

            if(dataBinding.edtAreaName.text.isNullOrEmpty().not() && dataBinding.edtDellCharge.text.isNullOrEmpty() && dataBinding.edtMinBill.text.isNullOrEmpty().not())
                viewModel.editArea(
                    AreaModel(
                    dataBinding.edtAreaName.text.toString(),
                    dataBinding.edtMinBill.text.toString().toFloat(),
                    dataBinding.edtDellCharge.text.toString().toFloat(),
                    dataBinding.edtManagerName.text.toString()
                )
                )
            else
                Toast.makeText(requireContext(),resources.getString(R.string.enter_valid_feilds),Toast.LENGTH_SHORT).show()
        }

        dataBinding.btnDelete.setOnClickListener {

            if(dataBinding.edtAreaName.text.isNullOrEmpty().not() && dataBinding.edtDellCharge.text.isNullOrEmpty() && dataBinding.edtMinBill.text.isNullOrEmpty().not())
                viewModel.deleteArea(AreaModel(
                    dataBinding.edtAreaName.text.toString(),
                    dataBinding.edtMinBill.text.toString().toFloat(),
                    dataBinding.edtDellCharge.text.toString().toFloat(),
                    dataBinding.edtManagerName.text.toString()
                ))
            else
                Toast.makeText(requireContext(),resources.getString(R.string.enter_valid_feilds),Toast.LENGTH_SHORT).show()
        }
    }
}
