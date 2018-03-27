package com.quoders.app.codewarschallenge.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import com.quoders.app.codewarschallenge.R
import com.quoders.app.codewarschallenge.ui.search.SearchActivity
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchViewUITest {

    @get:Rule
    var activityRule: ActivityTestRule<SearchActivity> = ActivityTestRule(SearchActivity::class.java)

/*    @Before
    fun cleanDb() {
        val context = InstrumentationRegistry.getTargetContext()
        var database = Room.databaseBuilder(context, CodewarsDatabase::class.java, "codewars_database").build()
        database.userDao().deleteAll()
    }*/

    @Test
    fun given_searchValidName_then_resultIsDisplayed() {
        onView(withId(R.id.editTextSearchName)).perform(typeText("Bubbler"))
        onView(withId(R.id.buttonSearch)).perform(click())
            onView(withId(R.id.recyclerViewSearchResult)).perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun given_searchValidName_and_resultIsDisplayed_when_ClickOnResult_then_displayChallenges() {
        onView(withId(R.id.editTextSearchName)).perform(typeText("Bubbler"))
        onView(withId(R.id.buttonSearch)).perform(click())
        onView(withId(R.id.recyclerViewSearchResult)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(isRoot()).perform(waitFor(10000))

        onView(withId(R.id.recyclerViewChallenges)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun given_validSearchAndDisplayedChallenges_when_selectChallenge_then_DisplayChallengeDetails() {
        onView(withId(R.id.editTextSearchName)).perform(typeText("Bubbler"))
        onView(withId(R.id.buttonSearch)).perform(click())
        onView(withId(R.id.recyclerViewSearchResult)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(isRoot()).perform(waitFor(8000))

        onView(withId(R.id.recyclerViewChallenges)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(isRoot()).perform(waitFor(4000))

        onView(withId(R.id.textViewDetail)).check(matches(isDisplayed()))
    }

    @Test
    fun given_validSearchAndResults_when_selectChallengesAuthored_then_displayAuthoredList() {
        onView(withId(R.id.editTextSearchName)).perform(typeText("Bubbler"))
        onView(withId(R.id.buttonSearch)).perform(click())
        onView(withId(R.id.recyclerViewSearchResult)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(isRoot()).perform(waitFor(8000))

        onView(withId(R.id.navigation_challenges_authored)).perform(click())

        onView(isRoot()).perform(waitFor(2000))

        onView(withId(R.id.recyclerViewChallenges)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(isRoot()).perform(waitFor(2000))

        onView(withId(R.id.textViewDetail)).check(matches(isDisplayed()))
    }

    @Test
    fun given_userNameNotExist_when_Search_then_displayErrorDialog() {
        onView(withId(R.id.editTextSearchName)).perform(typeText("aabbcc"))
        onView(withId(R.id.buttonSearch)).perform(click())
        onView(withText(R.string.dialog_error_message_user_not_found)).check(matches(isDisplayed()))
    }

    private fun waitFor(millis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "Wait for $millis milliseconds."
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }
}