package com.rahul.moviesearch.features.movie_details


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.rahul.moviesearch.R
import com.rahul.moviesearch.features.search.SearchActivity
import kotlinx.android.synthetic.main.activity_search.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MovieDetailsActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SearchActivity::class.java)

    @Test
    fun mainActivityTest() {
        Thread.sleep(1500)
        // Scroll to bottom of the recycler view
        val recyclerView = mActivityTestRule.activity.searchRecyclerView
        val itemCount = recyclerView.adapter!!.itemCount
        // Scroll to end of page with position
        onView(withId(R.id.searchRecyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))

        Thread.sleep(1500)

        onView(withId(R.id.searchRecyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Thread.sleep(1500)

        val textView = onView(
            allOf(
                withText("MovieSearch"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("MovieSearch")))

        val textView2 = onView(
            allOf(
                withText("Bat*21"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Bat*21")))

        val textView3 = onView(
            allOf(
                withText("1988"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("1988")))

        val textView4 = onView(
            allOf(
                withText("During the Vietnam War, Colonel Hambleton's aircraft is shot down over enemy territory and a frantic rescue operation ensues."),
                childAtPosition(
                    allOf(
                        withId(R.id.middleView),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("During the Vietnam War, Colonel Hambleton's aircraft is shot down over enemy territory and a frantic rescue operation ensues.")))

        val textView5 = onView(
            allOf(
                withText("William C. Anderson (book), William C. Anderson (screenplay), George Gordon (screenplay)"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("William C. Anderson (book), William C. Anderson (screenplay), George Gordon (screenplay)")))

        val textView6 = onView(
            allOf(
                withText("Peter Markle"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("Peter Markle")))

        val imageView = onView(
            allOf(
                withId(R.id.image),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView7 = onView(
            allOf(
                withText("Gene Hackman, Danny Glover, Jerry Reed, David Marshall Grant"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        2
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView7.check(matches(withText("Gene Hackman, Danny Glover, Jerry Reed, David Marshall Grant")))


    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }


}
