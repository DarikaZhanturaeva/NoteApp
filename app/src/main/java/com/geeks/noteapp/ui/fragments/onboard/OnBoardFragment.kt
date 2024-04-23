package com.geeks.noteapp.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.geeks.noteapp.R
import com.geeks.noteapp.data.Pref
import com.geeks.noteapp.databinding.FragmentOnBoardBinding
import com.geeks.noteapp.ui.adapter.OnBoardViewPagerAdapter

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        btnGetStarted()
        openHome()
    }


    private fun initialize() {
        binding.viewPager2.adapter = OnBoardViewPagerAdapter(this@OnBoardFragment)
        Pref.unit(requireContext())
    }


    private fun setupListener() = with(binding.viewPager2) {
        binding.tvSend.setOnClickListener {
            if (currentItem < 3) {
                setCurrentItem(currentItem + 2, true)
            }
        }
        binding.tvGetStarted.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }

    private fun btnGetStarted() = with(binding) {
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        tvSend.isVisible = true
                        tvGetStarted.isVisible = false
                    }

                    1 -> {
                        tvSend.isVisible = true
                        tvGetStarted.isVisible = false
                    }

                    2 -> {
                        tvSend.isVisible = false
                        tvGetStarted.isVisible = true
                    }
                }
                super.onPageSelected(position)
            }
        })
    }

    private fun openHome() {
        Pref.isOnBoardShown = true
    }

}