package com.indemand.fotd.viewmodel.splash

import com.indemand.fotd.analytics.receiver.AnalyticsReceiver
import com.indemand.fotd.domain.usecase.AppUpdateDialogUseCase
import com.indemand.fotd.domain.usecase.ConfigurationUseCase
import com.indemand.fotd.domain.usecase.GetAccessTokenUseCase
import com.indemand.fotd.domain.usecase.ValidateTokenUseCase
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.mockito.Mockito.mock
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SplashViewModelTest {

    private val testDispatchers = UnconfinedTestDispatcher()

    @BeforeTest
    fun beforeTest() {
        Dispatchers.setMain(testDispatchers)
    }

    @AfterTest
    fun afterTest() {
        Dispatchers.resetMain()
    }

    @Test
    fun `screen context has no parent when parentScreenContext is null`() {
        val configurationUseCase = mock<ConfigurationUseCase>()
        val appUpdateDialogUseCase = mock<AppUpdateDialogUseCase>()
        val getAccessTokenUseCase = mock<GetAccessTokenUseCase>()
        val validateTokenUseCase = mock<ValidateTokenUseCase>()
        val analyticsAggregator = mock<AnalyticsReceiver>()

        val splashViewModel = SplashViewModel(
            configurationUseCase = configurationUseCase,
            appUpdateDialogUseCase = appUpdateDialogUseCase,
            getAccessTokenUseCase = getAccessTokenUseCase,
            validateTokenUseCase = validateTokenUseCase,
            analyticsReceiver = analyticsAggregator,
            screenName = "Splash",
            parentScreenContext = null
        )

        val context = splashViewModel.screenContext
        assertEquals("Splash", context.screenName)
        assertNull(context.parentContext)
    }
}