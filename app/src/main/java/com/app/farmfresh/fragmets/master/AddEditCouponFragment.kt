package com.app.farmfresh.fragmets.master

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.farmfresh.R
import com.app.farmfresh.databinding.FragmentAddEditCouponBinding
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.repo.models.CouponModel
import com.app.farmfresh.viewmodels.master.CouponFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class AddEditCouponFragment : Fragment() {

    private lateinit var dataBinding : FragmentAddEditCouponBinding
    private lateinit var couponFragmentViewModel: CouponFragmentViewModel
    private  var couponModel : CouponModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_add_edit_coupon,container,false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        couponModel = arguments?.getSerializable("coupon_model") as CouponModel

        couponFragmentViewModel = ViewModelProvider(this)[CouponFragmentViewModel::class.java]


        dataBinding.edtCouponCode.setText(couponModel?.code)
        dataBinding.edtCouponCode.isEnabled = false
        dataBinding.edtCouponMinBill.setText(couponModel?.amount.toString())
        dataBinding.edtCouponnDetails.setText(couponModel?.desc)


        dataBinding.btnEdit.setOnClickListener {
            if(dataBinding.edtCouponCode.text.isNullOrEmpty().not() &&  dataBinding.edtCouponMinBill.text.isNullOrEmpty().not()
                && dataBinding.edtCouponnDetails.text.isNullOrEmpty().not())
                couponFragmentViewModel.editCoupon(
                    CouponModel(
                        dataBinding.edtCouponCode.text.toString(),
                        dataBinding.edtCouponMinBill.text.toString().toFloat(),
                        dataBinding.edtCouponnDetails.text.toString()
                    )
                ).observe(viewLifecycleOwner, Observer {
                    Snackbar.make(dataBinding.root,it.data, Snackbar.LENGTH_SHORT).show()
                })
            else
                Snackbar.make(dataBinding.root,resources.getString(R.string.enter_valid_feilds),
                    Snackbar.LENGTH_SHORT).show()
        }

        dataBinding.btnDelete.setOnClickListener {
            if(dataBinding.edtCouponCode.text.isNullOrEmpty().not() &&  dataBinding.edtCouponMinBill.text.isNullOrEmpty().not()
                && dataBinding.edtCouponnDetails.text.isNullOrEmpty().not())
                couponFragmentViewModel.deleteCoupon(
                    CouponModel(
                        dataBinding.edtCouponCode.text.toString(),
                        dataBinding.edtCouponMinBill.text.toString().toFloat(),
                        dataBinding.edtCouponnDetails.text.toString())
                ).observe(viewLifecycleOwner, Observer {
                    Snackbar.make(dataBinding.root,it.data,Snackbar.LENGTH_SHORT).show()
                })
            else
                Snackbar.make(dataBinding.root,resources.getString(R.string.enter_valid_feilds),Snackbar.LENGTH_SHORT).show()
        }

    }


}