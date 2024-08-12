package com.example.challengeconexa

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.challengeconexa.ui.fragment.UsersFragment
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UsersFragmentTest {

    @Test
    fun usersFragmentSetsUpRecyclerViewCorrectly() {
        // Arrange
        val scenario = launchFragmentInContainer<UsersFragment>(Bundle())

        // Act
        scenario.onFragment { fragment ->
            // Assert
            assertNotNull(fragment.binding.rvUsers.layoutManager)
            assertTrue(fragment.binding.rvUsers.layoutManager is LinearLayoutManager)
        }
    }
}