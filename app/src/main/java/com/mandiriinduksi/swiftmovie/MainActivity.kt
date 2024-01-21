package com.mandiriinduksi.swiftmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.mandiriinduksi.swiftmovie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id){
                R.id.homeFragment, R.id.exploreFragment, R.id.profileFragment -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }
                else -> binding.bottomNavigation.visibility = View.GONE
            }
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.nav_graph, true).build()
            when (it.itemId){
                R.id.bot_nav_menu_home -> {
                    navController.navigate(R.id.homeFragment, null, navOptions)
                    true
                }
                R.id.bot_nav_menu_search -> {
                    navController.navigate(R.id.exploreFragment, null, navOptions)
                    true
                }
                else -> false
            }
        }

    }
}