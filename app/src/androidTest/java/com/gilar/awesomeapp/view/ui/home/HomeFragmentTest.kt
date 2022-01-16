package com.gilar.awesomeapp.view.ui.home

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.util.HumanReadables
import androidx.test.espresso.util.TreeIterables
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gilar.awesomeapp.R
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import java.util.concurrent.TimeoutException


@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class HomeFragmentTest {

    private lateinit var scenario: FragmentScenario<HomeFragment>

    @Before
    fun setup() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_AwesomeApp) {
            HomeFragment().also { fragment ->
                // In addition to returning a new instance of our Fragment,
                // get a callback whenever the fragment’s view is created
                // or destroyed so that we can set the mock NavController
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }
    }

    @Test
    fun testFetchPhotosFromServer() {
        // Given launch fragment

        // When wait for api response
        Thread.sleep(5000)

        // Then RecyclerView will shown
        onView(withId(R.id.recyclerViewPhoto)).check(matches(isDisplayed()))
    }

}