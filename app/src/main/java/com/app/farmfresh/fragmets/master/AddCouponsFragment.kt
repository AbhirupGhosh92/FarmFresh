package com.app.farmfresh.fragmets.master

import android.os.Bundle
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
import com.app.farmfresh.databinding.FragmentAddCouponsBinding
import com.app.farmfresh.databinding.FragmentShowCouponsBinding
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.repo.models.CouponModel
import com.app.farmfresh.viewmodels.master.AreaFragmentViewModel
import com.app.farmfresh.viewmodels.master.CouponFragmentViewModel

class AddCouponsFragment : Fragment() {



    lateinit var viewModel: CouponFragmentViewModel
    private lateinit var databinding : FragmentAddCouponsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        databinding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_coupons, container, false)

        viewModel = ViewModelProvider(this).get(CouponFragmentViewModel::class.java)

        return databinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        databinding.btnSave.setOnClickListener {
            if(databinding.edtCouponAmount.text.isNullOrEmpty().not() && databinding.edtCouponCode.text.isNullOrEmpty().not() && databinding.edtCouponDesc.text.isNullOrEmpty().not()) {
                viewModel.addCoupon(
                    CouponModel(
                        databinding.edtCouponCode.text.toString(),
                        databinding.edtCouponAmount.text.toString().toFloat(),
                        databinding.edtCouponDesc.text.toString()
                    )
                ).observe(viewLifecycleOwner, Observer {
                    Toast.makeText(requireContext(),it.data, Toast.LENGTH_SHORT).show()
                })
            }
            else
                Toast.makeText(requireContext(),resources.getString(R.string.enter_valid_feilds),
                    Toast.LENGTH_SHORT).show()
        }

    }

}