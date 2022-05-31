package com.test.finaltask.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.test.finaltask.R
import com.test.finaltask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.topAppBar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.containerView) as NavHostFragment
        navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, _: Bundle? ->
            if (nd.id == nc.graph.startDestinationId) {
                binding.topAppBar.navigationIcon = null
            }
        }

        binding.topAppBar.setNavigationOnClickListener {
            navController.navigateUp()
        }
        setContentView(binding.root)
    }
}