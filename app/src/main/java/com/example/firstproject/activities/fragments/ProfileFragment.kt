package com.example.firstproject.activities.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.firstproject.R
import com.example.firstproject.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
   lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProfileBinding.inflate(inflater,container,false)

        val pref = this.getActivity()?.getSharedPreferences("User", AppCompatActivity.MODE_PRIVATE)
        val userId=pref?.getString("user_id","")!!
        val userName=pref?.getString("name","")

        binding.tvWelcome.text="Welcome $userName"

        return binding.root
    }


}