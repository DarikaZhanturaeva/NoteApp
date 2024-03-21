package com.geeks.noteapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.geeks.noteapp.R
import com.geeks.noteapp.data.Pref
import com.geeks.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)as NavHostFragment

        navController= navHostFragment.navController

        if (Pref.isOnBoardShown){
            navController.navigate(R.id.noteFragment)
        }else{
            navController.navigate(R.id.obBoardFragment)
        }
    }
}