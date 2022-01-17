package com.gilar.awesomeapp.view.ui.home

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gilar.awesomeapp.R
import com.gilar.awesomeapp.data.model.Photo
import com.gilar.awesomeapp.data.model.PhotoSource
import com.gilar.awesomeapp.view.ui.detail.DetailFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class DetailFragmentTest {

    private lateinit var scenario: FragmentScenario<DetailFragment>

    @Before
    fun setup() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        val photo = Photo(
            photographer = "Gilar",
            height = 100,
            width = 200,
            url = "https://media-exp1.licdn.com/dms/image/C5603AQFYYZpN6KM0mA/profile-displayphoto-shrink_800_800/0/1641739624068?e=1648080000&v=beta&t=8DkpWXuWe30QM5gtL4bwEWK7CR6q3gZ28HkhKgALO6o",
            alt = "Foto Gilar",
            src = PhotoSource(
                "https://media-exp1.licdn.com/dms/image/C5603AQFYYZpN6KM0mA/profile-displayphoto-shrink_800_800/0/1641739624068?e=1648080000&v=beta&t=8DkpWXuWe30QM5gtL4bwEWK7CR6q3gZ28HkhKgALO6o",
                "https://media-exp1.licdn.com/dms/image/C5603AQFYYZpN6KM0mA/profile-displayphoto-shrink_800_800/0/1641739624068?e=1648080000&v=beta&t=8DkpWXuWe30QM5gtL4bwEWK7CR6q3gZ28HkhKgALO6o",
                "https://media-exp1.licdn.com/dms/image/C5603AQFYYZpN6KM0mA/profile-displayphoto-shrink_800_800/0/1641739624068?e=1648080000&v=beta&t=8DkpWXuWe30QM5gtL4bwEWK7CR6q3gZ28HkhKgALO6o",
                "https://media-exp1.licdn.com/dms/image/C5603AQFYYZpN6KM0mA/profile-displayphoto-shrink_800_800/0/1641739624068?e=1648080000&v=beta&t=8DkpWXuWe30QM5gtL4bwEWK7CR6q3gZ28HkhKgALO6o",
                "https://media-exp1.licdn.com/dms/image/C5603AQFYYZpN6KM0mA/profile-displayphoto-shrink_800_800/0/1641739624068?e=1648080000&v=beta&t=8DkpWXuWe30QM5gtL4bwEWK7CR6q3gZ28HkhKgALO6o",
                "https://media-exp1.licdn.com/dms/image/C5603AQFYYZpN6KM0mA/profile-displayphoto-shrink_800_800/0/1641739624068?e=1648080000&v=beta&t=8DkpWXuWe30QM5gtL4bwEWK7CR6q3gZ28HkhKgALO6o",
                "https://media-exp1.licdn.com/dms/image/C5603AQFYYZpN6KM0mA/profile-displayphoto-shrink_800_800/0/1641739624068?e=1648080000&v=beta&t=8DkpWXuWe30QM5gtL4bwEWK7CR6q3gZ28HkhKgALO6o",
                "https://media-exp1.licdn.com/dms/image/C5603AQFYYZpN6KM0mA/profile-displayphoto-shrink_800_800/0/1641739624068?e=1648080000&v=beta&t=8DkpWXuWe30QM5gtL4bwEWK7CR6q3gZ28HkhKgALO6o",
            ),
            photographerUrl = "https://gilarps.medium.com/"
        )
        val fragmentArgs = bundleOf("photo" to photo)
        scenario =
            launchFragmentInContainer(
                fragmentArgs = fragmentArgs,
                themeResId = R.style.Theme_AwesomeApp
            ) {
                DetailFragment().also { fragment ->
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
    fun testPhotoDetails() {
        // Given launch fragment

        // When wait for api response
        Thread.sleep(5000)

        // Then RecyclerView will shown
        onView(withId(R.id.tvCaptionPhotographer)).check(matches(isDisplayed()))
    }

}