/*
 * Created by Farhan Tanvir on 12/5/21, 5:08 PM
 * Last modified 12/5/21, 5:06 PM
 * Contact : farhantanvir65@gmail.com
 */


package com.farhan.tanvir.pexels.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.farhan.tanvir.pexels.R
import com.farhan.tanvir.pexels.utils.network.NetworkStatusHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch(Dispatchers.IO) {
            NetworkStatusHelper().setNetworkChecker(this@MainActivity)
        }
    }
}