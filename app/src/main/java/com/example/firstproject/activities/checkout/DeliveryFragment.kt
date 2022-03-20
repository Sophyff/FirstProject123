package com.example.firstproject.activities.checkout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.firstproject.R
import com.example.firstproject.databinding.DialogAddAddressBinding
import com.example.firstproject.databinding.FragmentCartItemBinding
import com.example.firstproject.databinding.FragmentDeliveryBinding

class DeliveryFragment : Fragment() {
    lateinit var binding: FragmentDeliveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDeliveryBinding.inflate(layoutInflater)

        val pref = this.getActivity()?.getSharedPreferences("User", AppCompatActivity.MODE_PRIVATE)
        val userId=pref?.getString("user_id","")
        Log.d("name","$userId")

        binding.btnAddAddress.setOnClickListener {
            showInputDialog()
        }

        return binding.root
    }

    private fun showInputDialog() {
        val dialogBinding = DialogAddAddressBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(binding.root.context)
            .setView(dialogBinding.root)
        val dialog = builder.create()
        dialog.show()

        dialogBinding.btnSave.setOnClickListener {
            val title = dialogBinding.etAddressTitle.text.toString()
            val address = dialogBinding.etAddressContent.text.toString()
            Log.d("tag","address title is $title, address is $address")
            dialog.dismiss()
            //todo api call to add address

        }

        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

    }
}