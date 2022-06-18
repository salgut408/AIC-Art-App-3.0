package com.example.android.aicartapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.aicartapp.databinding.ActivityArtBinding
import com.example.android.aicartapp.db.ArtworkDatabase
import com.example.android.aicartapp.repository.ArtRepository
import com.example.android.aicartapp.ui.MainArtViewModel
import com.example.android.aicartapp.ui.MainArtViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtBinding
    lateinit var viewModel: MainArtViewModel
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val artRepository = ArtRepository(ArtworkDatabase(this))
        val viewModelProviderFactory = MainArtViewModelProviderFactory(application, artRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainArtViewModel::class.java)

        binding = ActivityArtBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val navView: BottomNavigationView = binding.navView
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
         appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_favorites
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)




    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }

}