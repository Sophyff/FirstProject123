package com.example.firstproject.activities.checkout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.firstproject.R
import com.example.firstproject.activities.ProductByCategoryActivity
import com.example.firstproject.adapter.AddressAdapter
import com.example.firstproject.adapter.CategoryAdapter
import com.example.firstproject.data.Constants
import com.example.firstproject.data.local.DeliveryAddress
import com.example.firstproject.data.remote.*
import com.example.firstproject.databinding.DialogAddAddressBinding
import com.example.firstproject.databinding.FragmentCartItemBinding
import com.example.firstproject.databinding.FragmentDeliveryBinding
import com.google.gson.Gson
import org.json.JSONObject
import java.net.URLEncoder

class DeliveryFragment : Fragment() {
    lateinit var binding: FragmentDeliveryBinding
    var userId=""
    lateinit var viewModel: CheckoutViewModel
    lateinit var queue:RequestQueue
    lateinit var addressList:List<Addresse>
    lateinit var adapter : AddressAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDeliveryBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity()).get(CheckoutViewModel::class.java)

        val pref = this.getActivity()?.getSharedPreferences("User", AppCompatActivity.MODE_PRIVATE)
         userId=pref?.getString("user_id","")!!
        Log.d("user id","$userId")
        queue= Volley.newRequestQueue(binding.root.context)
        binding.rvAddress.layoutManager=LinearLayoutManager(binding.root.context)
     //   getAddressList()

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
            val addr=DeliveryAddress(title,address)
            viewModel.addr.postValue(addr)     //save address to view model

            binding.tvTitle.text=title
            binding.tvAddress.text=address

        //    addAddressApi(title,address)

            dialog.dismiss()

        }


        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

    }

    private fun addAddressApi(title:String, address:String){
        /***
        //todo api call to add address
        val url = "${Constants.BASE_URL}User/address"

        val requestData: JSONObject = JSONObject()
        requestData.put("user_id",userId)
        requestData.put("email_id",title)
        requestData.put("password",address)

        val request= JsonObjectRequest(
        Request.Method.POST,
        url,
        requestData,
        {
        val status=it.getInt("status")
        val message=it.getString("message")
        if(status==0){
        Toast.makeText(binding.root.context,message, Toast.LENGTH_LONG).show()
        Log.d("address","sucess message $message")
        getAddressList()
        adapter.notifyDataSetChanged()

        }else{
        Toast.makeText(binding.root.context,message, Toast.LENGTH_LONG).show()
        }

        },{
        error: VolleyError ->
        error.printStackTrace()
        Toast.makeText(binding.root.context,"$error", Toast.LENGTH_LONG).show()
        }
        )
        queue.add(request)
         ***/
    }

    private fun getAddressList(){
        val url = "${Constants.BASE_URL}User/address/$userId"
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val response = gson.fromJson(it, getAddressResponse::class.java)

                addressList= response.addresses

                Log.d("tag", "the category list is $addressList")
                adapter = AddressAdapter(addressList)
                binding.rvAddress.adapter = adapter
                adapter.setOnItemSelectedListener{
                        address, position ->
                        //todo send data in summary for place order
                }

            },
            {
                it.printStackTrace()
                // Toast.makeText(this, "Error is : $it", Toast.LENGTH_LONG).show()
            }
        )
        val retryPolicy = DefaultRetryPolicy(10 * 1000, 3, 1.5f)
        request.retryPolicy = retryPolicy
        queue.add(request)
    }
}