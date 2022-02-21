/*
 * Created by Farhan Tanvir on 1/6/21 12:18 AM
 *  Copyright (c) Farhan Tanvir 2021 . All rights reserved.
 *  Last modified 1/6/21 12:18 AM
 */

package com.farhan.tanvir.pexels.utils

import android.content.Context
import android.widget.Toast

object ToastUtil {

    /**
     * @param mContext Context of screen
     * @param message Message text which will be show in Toast
     */
    fun showToastShortLength(mContext: Context, message: String){
        Toast.makeText(mContext.applicationContext,message, Toast.LENGTH_SHORT).show()
    }

    /**
     * @param mContext Context of screen
     * @param message Message text which will be show in Toast
     */
    fun showToastLongLength(mContext: Context, message: String){
        Toast.makeText(mContext.applicationContext,message, Toast.LENGTH_SHORT).show()
    }
}