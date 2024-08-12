package com.example.challengeconexa

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.challengeconexa.service.model.New
import com.example.challengeconexa.ui.adapter.NewsAdapter
import com.example.challengeconexa.utils.Result
import com.example.challengeconexa.ui.fragment.NewsFragment
import com.example.challengeconexa.ui.view_model.NewsViewModel
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewsFragmentTest {

    @Test
    fun newsFragmentSetsUpAdapterCorrectly() {
        // Arrange
        val scenario = launchFragmentInContainer<NewsFragment>(Bundle())

        // Act
        scenario.onFragment { fragment ->
            // Assert
            assertNotNull(fragment.binding.rvNews.adapter)
            assertTrue(fragment.binding.rvNews.adapter is NewsAdapter)
        }
    }
}