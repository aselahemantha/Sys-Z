package com.example.uilibrary.view.dashboard.main

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.basesdk.domain.model.InstitutionConfig
import com.example.uilibrary.banners.SlowConnection
import com.example.uilibrary.common.BottomNavBar
import com.example.uilibrary.coreui.NavigationItem
import com.example.uilibrary.coreui.Routes
import com.example.uilibrary.elements.popup.snackbar.CustomSnackbarHost
import com.example.uilibrary.elements.popup.snackbar.CustomSnackbarVisuals
import com.example.uilibrary.elements.popup.snackbar.SnackbarSeverity
import com.example.uilibrary.elements.popup.snackbar.SnackbarViewEvent
import com.example.uilibrary.util.enums.AppTheme
import com.example.uilibrary.view.dashboard.main.bottomBar.Navigation
import com.example.uilibrary.view.dashboard.main.bottomBar.NavigationEffects
import com.example.uilibrary.view.dashboard.main.bottomBar.composable
import com.example.uilibrary.view.dashboard.main.bottomBar.isOnboardRoute
import com.example.uilibrary.view.dashboard.main.bottomBar.isShowBottomNavigation
import com.example.uilibrary.view.dashboard.main.bottomBar.isTab01Route
import com.example.uilibrary.view.dashboard.tab01.Tab01Screen
import com.example.uilibrary.view.dashboard.tab02.Tab02Screen
import com.example.uilibrary.view.dashboard.tab03.Tab03Screen
import com.example.uilibrary.view.dashboard.tab04.Tab04Screen
import com.example.uilibrary.view.dashboard.tab05.Tab05Screen
import com.example.uilibrary.view.onboard.login.LoginScreen
import com.example.uilibrary.view.onboard.splash.SplashScreen
import com.gtn.basesdk.util.enums.ConnectionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GetDashboardContent(
    destination: String?,
    theme: AppTheme = AppTheme.LIGHT,
    onTheme: (AppTheme) -> Unit,
    navController: NavHostController = rememberNavController(),
    viewmodel: DashBoardViewModel = hiltViewModel(),
) {
    var dashboardContext = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // detect tab-01
    var isOnTabo1Screen = remember { mutableStateOf(false) }
    // assume detect default open into login always
    var isOnboardRoute = remember { mutableStateOf(true) }
    val isSideDrawerVisible = remember { mutableStateOf(false) }
    val showBottomNavBar = remember { mutableStateOf(true) }
    val baseNotificationState by BaseViewCoreModel.baseNoficationState
        .collectAsState()

    val cachedConfig =
        remember {
            mutableStateOf<InstitutionConfig?>(
                BaseViewCoreModel.institutionConfig
                    ?.let { it },
            )
        }

    NavigationEffects(
        navigationChannel = viewmodel.navigationChannel,
        navHostController = navController,
    )

    // detect navigation updates
    LaunchedEffect(navBackStackEntry?.destination) {
        showBottomNavBar.value = isShowBottomNavigation(navBackStackEntry = navBackStackEntry, destination = destination)
        isOnboardRoute.value = isOnboardRoute(navBackStackEntry = navBackStackEntry, destination = destination)
        isOnTabo1Screen.value = isTab01Route(navBackStackEntry = navBackStackEntry, destination = destination)
        viewmodel.onCompleteOnboard(isComplete = !isOnboardRoute.value)
    }

    val scope = rememberCoroutineScope()
    val fadeInAnimation = fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing))
    val fadeOutAnimation = fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing))

    // pull
    var otherPull by remember { mutableStateOf({}) }
    var isRefreshing by remember { mutableStateOf(false) }
    var lastRefreshTime by remember { mutableStateOf(0L) }
    val throttleInterval = 3000L

    val pullRefreshState =
        rememberPullRefreshState(
            isRefreshing,
            {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastRefreshTime >= throttleInterval) {
                    lastRefreshTime = currentTime
                    // Update the last refresh time
                    isRefreshing = true
//                    viewmodel.onPull(onThen = {
//                        otherPull()
//                    })
                    scope.launch {
                        delay(2000)
                        isRefreshing = false
                    }
                }
            },
        )

    // snack
    val snackBarEventState by viewmodel.snackBarEventState.distinctUntilChanged().observeAsState(emptyList())
    val snackBarEvents: MutableState<List<SnackbarViewEvent>> = remember { mutableStateOf(emptyList()) }
    var snackBarAlign by remember { mutableStateOf(Alignment.BottomCenter) }
    snackBarEventState?.let { snackBarEvents.value = it }
    LaunchedEffect(snackBarEventState) {
        snackBarEvents.value.let {
            it.reversed().onEach { eventData ->
                snackBarAlign = eventData.align
                scope.launch {
                    val result =
                        viewmodel.baseSnackbarHostState.showSnackbar(
                            visuals =
                                CustomSnackbarVisuals(
                                    actionTag = eventData.actionTag,
                                    action = { eventData.action(eventData.eventId, it) },
                                    close = { eventData.close(eventData.eventId) },
                                    duration =
                                        if (eventData.severity.equals(
                                                SnackbarSeverity.CONNECTIVITY_ISSUE,
                                            )
                                        ) {
                                            SnackbarDuration.Indefinite
                                        } else {
                                            SnackbarDuration.Short
                                        },
                                    withDismissAction = false,
                                    severity = eventData.severity,
                                    annotatedMessage = eventData.annotatedMessage,
                                    align = eventData.align,
                                ),
                        )
                    when (result) {
                        SnackbarResult.ActionPerformed -> {}
                        SnackbarResult.Dismissed -> {
                            viewmodel.removeSnackbarEvent(key = eventData.key, eventId = eventData.eventId)
                        }
                    }
                    delay(4000L)
                    viewmodel.removeSnackbarEvent(removeLast = true)
                }
            }
        }
    }

    // bottomsheets
    var showLogoutSheet by remember { mutableStateOf(false) }
    var showLogoutSheetLoading by remember { mutableStateOf(false) }
    val welcomePrompted by BaseViewCoreModel.isWelcomePrompted
        .asStateFlow()
        .collectAsState(false)

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount > 0) {
                        if (isOnTabo1Screen.value) {
                            scope.launch {
                                isSideDrawerVisible.value = true
                            }
                        } else {
                            scope.launch {
                                isSideDrawerVisible.value = false
                            }
                        }
                    } else if (dragAmount < 0) {
                        scope.launch {
                            isSideDrawerVisible.value = false
                        }
                    }
                }
            },
    ) {
        Scaffold(
            modifier =
            Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState, enabled = !isOnboardRoute.value),
            contentWindowInsets = WindowInsets(0),
            topBar = {},
            bottomBar = {
                if (showBottomNavBar.value) {
                    BottomNavBar(
                        selectedTabIndex = viewmodel.selectedBottomNavTab.value,
                        onSelect = { route ->
                            viewmodel.onBottomNavigate(navController, selectedRoute = route)
                        },
                        institutionConfig = cachedConfig.value,
                        baseNotification = baseNotificationState,
                    )
                }
            },
            snackbarHost = {
                CustomSnackbarHost(
                    hostState = viewmodel.baseSnackbarHostState,
                    modifier = Modifier.align(snackBarAlign),
                )
            },
        ) { paddingVal ->
            Box {
                Column(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = paddingVal.calculateBottomPadding()),
                ) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        if (destination != null) {
                            when (destination) {
                                Routes.TAB_01.route ->
                                    Tab01Screen(
//                                        onDrawer = {
//                                            if (isOnTabo1Screen.value) {
//                                                scope.launch {
//                                                    isSideDrawerVisible.value = true
//                                                }
//                                            }
//                                        },
//                                        onShortcut = { route ->
//                                            viewmodel.onBottomNavigate(navController, selectedRoute = route)
//                                        },
                                    )

                                Routes.TAB_02.route ->
                                    Tab02Screen(
//                                        onShortcut = { route ->
//                                            viewmodel.onBottomNavigate(navController, selectedRoute = route)
//                                        },
                                    )

                                Routes.TAB_03.route -> Tab03Screen()
                                Routes.TAB_04.route -> Tab04Screen()
                                Routes.TAB_05.route -> Tab05Screen()
                            }
                        } else {
                            Navigation(
                                navController = navController,
                                startDestination = NavigationItem.SplashScreen,
                            ) {
                                composable(destination = NavigationItem.SplashScreen) {
                                    SplashScreen()
                                }
                                composable(destination = NavigationItem.LoginScreen) {
                                    LoginScreen()
                                }
//                                composable(destination = NavigationItem.ForgotPasswordScreen) {
//                                    ForgotPasswordView()
//                                }
//                                composable(destination = NavigationItem.FPConfirmationScreen) {
//                                    it.arguments.let {
//                                        if (it != null) {
//                                            FPConfirmationView(
//                                                username = it.getString("username") as String,
//                                            )
//                                        }
//                                    }
//                                }
                                composable(destination = NavigationItem.Tab01) {
                                    Tab01Screen(
//                                        onDrawer = {
//                                            if (isOnTabo1Screen.value) {
//                                                scope.launch {
//                                                    isSideDrawerVisible.value = true
//                                                }
//                                            }
//                                        },
//                                        onShortcut = { route ->
//                                            viewmodel.onBottomNavigate(navController, selectedRoute = route)
//                                        },
//                                        onPull = { otherPull = { it() } },
                                    )
                                }
                                composable(destination = NavigationItem.Tab02) {
                                    Tab02Screen(
//                                        onShortcut = { route ->
//                                            viewmodel.onBottomNavigate(navController, selectedRoute = route)
//                                        },
//                                        onPull = { otherPull = { it() } },
                                    )
                                }
                                composable(destination = NavigationItem.Tab03) {
                                    Tab03Screen(
                                    )
                                }
                                composable(destination = NavigationItem.Tab04) {
                                    Tab04Screen()
                                }
                                composable(destination = NavigationItem.Tab05) {
                                    Tab05Screen()
                                }
                            }
                        }
                    }
                }
                SlowConnection(isVisible = baseNotificationState.networkConnectivityState.equals(
                    ConnectionState.SLOW))
                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                )
            }
        }
    }
}
