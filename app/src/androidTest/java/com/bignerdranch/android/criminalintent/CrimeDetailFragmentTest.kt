package com.bignerdranch.android.criminalintent

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CrimeDetailFragmentTest {

    // Define an instance of FragmentScenario called scenario  https://www.simplifiedcoding.net/android-espresso-tutorial/
    private lateinit var scenario: FragmentScenario<CrimeDetailFragment>

    // launchFragmentInContainer: https://developer.android.com/guide/fragments/test
    @Before
    fun setUp() {
        scenario = launchFragmentInContainer()
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    // Run a test to see if the title is not empty for the crimeTitle.doOnTextChanged listener when text is entered
    @Test
    fun crimeTitleUpdatesAfterTextEntered() {
        val testString = "Pen Thievery"

        Espresso.onView(withId(R.id.crime_title))
            .perform(typeText(testString))      // Perform an Action on a View (typeText): https://developer.android.com/training/testing/espresso/basics
        scenario.onFragment { fragment ->
            assertEquals(testString, fragment.crime.title) // See if the strings match each other; title should be a string
        }
    }

    // Run a test to see if the crime.isSolved property changes to true when the crimeSolved Checkbox is checked
    @Test
    fun crimeIsSolvedPropertySetToTrueWhenCrimeSolvedCheckBoxChecked() {
        val testIsCrimeSolvedTrue = true

        Espresso.onView(withId(R.id.crime_solved))
            .perform(click())
        scenario.onFragment { fragment ->
            assertEquals(testIsCrimeSolvedTrue, fragment.crime.isSolved)

        }
    }
}