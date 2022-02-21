/*
 * Created by Farhan Tanvir on 1/6/21 12:21 AM
 *  Copyright (c) Farhan Tanvir 2021 . All rights reserved.
 *  Last modified 1/6/21 12:21 AM
 */

package com.farhan.tanvir.pexels.utils.network

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.farhan.tanvir.pexels.utils.ToastUtil
import kotlinx.coroutines.flow.collect
import kotlin.properties.Delegates


class NetworkStatusHelper {
    private var backOnline = false
    private lateinit var mContext: Context
    private lateinit var networkListener: NetworkListener
    var networkStatus by Delegates.notNull<Boolean>()

    fun setNetworkChecker(activity: AppCompatActivity){
        mContext = activity
      activity.lifecycleScope.launchWhenStarted {
            networkListener = NetworkListener()
            networkListener.checkNetworkAvailability(activity)
                .collect { status ->
                    showNetworkStatus(status)
                }
        }
    }

     private fun showNetworkStatus(status: Boolean) {
         networkStatus = status
        if (!status) {
            ToastUtil.showToastLongLength(mContext,"インターネットがない。インターネットをオンにしてください。")
            backOnline = true
        } else if (status && backOnline) {
            backOnline = false
        }
    }
}