package com.rahul.moviesearch

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
open class BaseTest : KoinTest {
    var context: Context? = ApplicationProvider.getApplicationContext<MyApplication>()

    @Before
    open fun beforeEach() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    open fun afterEach() {
        stopKoin()
    }

}