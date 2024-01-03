package com.duongph.moneynote.presenter

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.duongph.moneynote.gone
import com.duongph.moneynote.show
import com.duongph.moneynote.viewModels
import com.example.mynotehilt.R
import com.example.mynotehilt.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val loadingDialog: ProgressDialog by lazy {
        ProgressDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            uiState.observe(this@MainActivity) {
                if (it) {
                    showLoading()
                } else {
                    hideLoading()
                }
            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.add_category,
                R.id.edit_note_frg,
                R.id.list_category ->
                    navView.gone()

                else -> navView.show()
            }
        }
    }

    override fun onBackPressed() {
        findNavController(R.id.nav_host_fragment_activity_main).popBackStack()
//        super.onBackPressed()
    }

    fun showLoading() {
        loadingDialog.show()
    }

    fun hideLoading() {
        loadingDialog.dismiss()
    }
}