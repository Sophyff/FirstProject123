package com.example.firstproject.activities.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.firstproject.R
import com.example.firstproject.databinding.FragmentPaymentBinding


class PaymentFragment : Fragment() {
    lateinit var binding:FragmentPaymentBinding
    lateinit var viewModel: CheckoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentPaymentBinding.inflate(layoutInflater)

        viewModel= ViewModelProvider(requireActivity()).get(CheckoutViewModel::class.java)
        selectPayment()
        return binding.root
    }

    private fun selectPayment(){
        binding.rgPaymentType.setOnCheckedChangeListener { radioGroup, id ->
            var selectedPlan = ""
            when(id) {
                R.id.rb_cash -> selectedPlan = "cash on delivery"
                R.id.rb_internet -> selectedPlan = "internet backing"
                R.id.rb_debit-> selectedPlan = "debit / credit"
                R.id.rb_paypal -> selectedPlan = "Pay Pal"
            }
            viewModel.paymentOption.postValue(selectedPlan)
        }

    }

}