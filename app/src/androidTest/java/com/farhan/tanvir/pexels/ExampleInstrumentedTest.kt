/*
 * Created by Farhan Tanvir on 12/5/21, 7:07 PM
 * Last modified 12/5/21, 7:00 PM
 * Contact : farhantanvir65@gmail.com
 */

package com.farhan.tanvir.pexels

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.farhan.tanvir.pexels", appContext.packageName)
    }
}