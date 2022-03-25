package com.example.firstproject.activities.checkout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.firstproject.activities.OrderDetailActivity
import com.example.firstproject.adapter.CheckoutItemAdapter
import com.example.firstproject.data.Constants
import com.example.firstproject.data.local.*
import com.example.firstproject.data.remote.Addresse
import com.example.firstproject.data.remote.OrderDetail
import com.example.firstproject.databinding.FragmentSummaryBinding
import com.google.gson.Gson
import org.json.JSONObject

class SummaryFragment : Fragment() {
    lateinit var binding: FragmentSummaryBinding
    lateinit var dao: CartProductDao
    lateinit var productList:ArrayList<CartProduct>
    lateinit var adapter: CheckoutItemAdapter
    lateinit var queue:RequestQueue
    lateinit var viewModel:CheckoutViewModel
    var sum = 0f
    var paymentOption=""
    lateinit var address: DeliveryAddress
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSummaryBinding.inflate(layoutInflater)
        binding.rvCheckoutItems.layoutManager = LinearLayoutManager(binding.root.getContext())
        queue= Volley.newRequestQueue(binding.root.context)
        viewModel= ViewModelProvider(requireActivity()).get(CheckoutViewModel::class.java)

        viewModel.addr.observe(viewLifecycleOwner){
            binding.tvAddressTitle.text=it.title
            binding.tvAddress.text=it.address    //set the address value
            address= DeliveryAddress(it.title, it.address)
        }

       viewModel.paymentOption.observe(viewLifecycleOwner){
           binding.tvPaymentOption.text=it
           paymentOption=it
       }

        dao= CartProductDao(binding.root.context)
        productList=dao.getCartProducts()
        adapter= CheckoutItemAdapter(productList)
        binding.rvCheckoutItems.adapter=adapter

        calculateTotalAmount()
        binding.btnPlaceOrder.setOnClickListener {
            placeOrder()
        }
        return binding.root
    }

     private fun calculateTotalAmount() {

        productList.forEach{
            sum = sum + it.amount
        }
        binding.tvTotalAmount.text = "\$ $sum"
    }

    private fun placeOrder(){
        val url = "${Constants.BASE_URL}Order"

       // var requestData: JSONObject = JSONObject()
        val requestData=convertData()
        Log.d("summary", "the request data is ${requestData.toString()}")
        val request= JsonObjectRequest(
            Request.Method.POST,
            url,
            requestData,
            {
                val status=it.getInt("status")

                if(status==0){
                    dao.deleteAll() //delete all items in cart
                    val orderId=it.getString("order_id")
                   Log.d("summary","order place sucessfully, order id is $orderId")
                    val intent= Intent(binding.root.context, OrderDetailActivity::class.java)
                    intent.putExtra("order_id",orderId)
                    startActivity(intent)


                }else{
                    val message=it.getString("message")
                    Log.d("summary","error message $message")
                }
            },{
                    error: VolleyError ->
                error.printStackTrace()
                Toast.makeText(binding.root.context,"$error", Toast.LENGTH_LONG).show()
            }
        )


        queue.add(request)
    }

    private fun convertData():JSONObject{
        val pref = this.getActivity()?.getSharedPreferences("User", AppCompatActivity.MODE_PRIVATE)
        val userId=pref?.getString("user_id","")?.toInt()!!

        var list=mutableListOf<Item>()
        for(item in productList){
            list.add(Item(item.item_id.toInt(),item.quantity,item.price))
        }

      //  val address= DeliveryAddress("Home","1280 walnut street")

        var bill=Bill(sum,address,list,paymentOption,userId)

        val gson=Gson()
        val itemObject=gson.toJson(bill,Bill::class.java)
        Log.d("summary","itemObject is ${itemObject}")

        val requestData: JSONObject = JSONObject(itemObject)


        return requestData
    }
}