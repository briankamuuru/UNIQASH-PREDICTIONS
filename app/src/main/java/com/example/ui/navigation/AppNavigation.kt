package com.example.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MainSection
import com.example.model.VipCategory
import com.example.ui.components.UniQashHeader
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.FreeTipsScreen
import com.example.ui.screens.HistoryCategoryDetailScreen
import com.example.ui.screens.HistoryVipHubScreen
import com.example.ui.screens.PrivacyPolicyScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.VipSubCategoryScreen
import com.example.ui.screens.VipTipsScreen
import com.example.ui.theme.UniQashBgClean
import com.example.ui.theme.UniQashBlueDark
import com.example.ui.theme.UniQashBluePrimary
import com.example.ui.theme.UniQashBorderSubtle
import com.example.ui.theme.UniQashGold
import com.example.ui.theme.UniQashGoldLight
import com.example.ui.theme.UniQashTextMuted
import com.example.ui.theme.UniQashTextPrimary
import com.example.viewmodel.PredictionsViewModel

sealed class ScreenDestination {
    data object FreeTips : ScreenDestination()

    data object VipDrawers : ScreenDestination()
    data class VipSubCategory(val category: VipCategory) : ScreenDestination()
    data object HistoryHub : ScreenDestination()
    data class HistoryCategory(val category: VipCategory) : ScreenDestination()

    data object Settings : ScreenDestination()
    data object PrivacyPolicy : ScreenDestination()
    data object About : ScreenDestination()
}

@Composable
fun MainAppContainer(
    viewModel: PredictionsViewModel,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val strings by viewModel.strings.collectAsState()

    var currentScreen by remember { mutableStateOf<ScreenDestination>(ScreenDestination.FreeTips) }
    var selectedTab by remember { mutableStateOf(MainSection.FREE) }

    // Layout Direction support (RTL for Arabic, LTR for others)
    val layoutDirection = if (currentLanguage.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        // Back Navigation Handler
        BackHandler(
            enabled = currentScreen !is ScreenDestination.FreeTips &&
                    currentScreen !is ScreenDestination.VipDrawers &&
                    currentScreen !is ScreenDestination.Settings
        ) {
            when (currentScreen) {
                is ScreenDestination.VipSubCategory -> currentScreen = ScreenDestination.VipDrawers
                is ScreenDestination.HistoryCategory -> currentScreen = ScreenDestination.HistoryHub
                is ScreenDestination.HistoryHub -> currentScreen = ScreenDestination.VipDrawers
                is ScreenDestination.PrivacyPolicy -> currentScreen = ScreenDestination.Settings
                is ScreenDestination.About -> currentScreen = ScreenDestination.Settings
                else -> {}
            }
        }

        // Header Title Calculation
        val headerTitle = when (val screen = currentScreen) {
            is ScreenDestination.FreeTips -> strings.appName
            is ScreenDestination.VipDrawers -> strings.navVipTips
            is ScreenDestination.VipSubCategory -> when (screen.category) {
                VipCategory.TWO_PLUS -> strings.vipTwoPlus
                VipCategory.THREE_PLUS -> strings.vipThreePlus
                VipCategory.FIVE_PLUS -> strings.vipFivePlus
                VipCategory.SPECIAL -> strings.vipSpecial
            }
            is ScreenDestination.HistoryHub -> strings.vipHistory
            is ScreenDestination.HistoryCategory -> when (screen.category) {
                VipCategory.TWO_PLUS -> strings.historyTwoPlus
                VipCategory.THREE_PLUS -> strings.historyThreePlus
                VipCategory.FIVE_PLUS -> strings.historyFivePlus
                VipCategory.SPECIAL -> strings.historySpecial
            }
            is ScreenDestination.Settings -> strings.navSettings
            is ScreenDestination.PrivacyPolicy -> strings.itemPrivacyPolicy
            is ScreenDestination.About -> strings.itemAbout
        }

        val showBackButton = when (currentScreen) {
            is ScreenDestination.VipSubCategory,
            is ScreenDestination.HistoryHub,
            is ScreenDestination.HistoryCategory,
            is ScreenDestination.PrivacyPolicy,
            is ScreenDestination.About -> true
            else -> false
        }

        Scaffold(
            modifier = modifier.fillMaxSize(),
            containerColor = UniQashBgClean,
            topBar = {
                UniQashHeader(
                    title = headerTitle,
                    showBackButton = showBackButton,
                    onBackClick = {
                        when (currentScreen) {
                            is ScreenDestination.VipSubCategory -> currentScreen = ScreenDestination.VipDrawers
                            is ScreenDestination.HistoryCategory -> currentScreen = ScreenDestination.HistoryHub
                            is ScreenDestination.HistoryHub -> currentScreen = ScreenDestination.VipDrawers
                            is ScreenDestination.PrivacyPolicy -> currentScreen = ScreenDestination.Settings
                            is ScreenDestination.About -> currentScreen = ScreenDestination.Settings
                            else -> {}
                        }
                    },
                    onResetClick = { viewModel.resetDemoData() }
                )
            },
            bottomBar = {
                // Exactly three main sections: Free Tips, VIP Tips, Settings
                // Wrapped in Surface with UniQashBlueDark to prevent any white background covering or clipping
                androidx.compose.material3.Surface(
                    color = UniQashBlueDark,
                    shadowElevation = 8.dp,
                    border = androidx.compose.foundation.BorderStroke(1.dp, UniQashGold.copy(alpha = 0.35f))
                ) {
                    NavigationBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("bottom_nav_bar"),
                        containerColor = UniQashBlueDark,
                        tonalElevation = 0.dp,
                        windowInsets = androidx.compose.material3.NavigationBarDefaults.windowInsets
                    ) {
                        // 1. Free Tips
                        val isFreeSelected = currentScreen is ScreenDestination.FreeTips
                        NavigationBarItem(
                            selected = isFreeSelected,
                            onClick = {
                                selectedTab = MainSection.FREE
                                currentScreen = ScreenDestination.FreeTips
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.SportsSoccer,
                                    contentDescription = strings.navFreeTips,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = strings.navFreeTips,
                                    fontSize = 12.sp,
                                    fontWeight = if (isFreeSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            alwaysShowLabel = true,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = UniQashGoldLight,
                                selectedTextColor = UniQashGoldLight,
                                indicatorColor = UniQashBluePrimary,
                                unselectedIconColor = Color.White.copy(alpha = 0.85f),
                                unselectedTextColor = Color.White.copy(alpha = 0.85f)
                            ),
                            modifier = Modifier.testTag("nav_free_tips")
                        )

                        // 2. VIP Tips
                        val isVipSelected = currentScreen is ScreenDestination.VipDrawers ||
                                currentScreen is ScreenDestination.VipSubCategory ||
                                currentScreen is ScreenDestination.HistoryHub ||
                                currentScreen is ScreenDestination.HistoryCategory
                        NavigationBarItem(
                            selected = isVipSelected,
                            onClick = {
                                selectedTab = MainSection.VIP
                                currentScreen = ScreenDestination.VipDrawers
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Diamond,
                                    contentDescription = strings.navVipTips,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = strings.navVipTips,
                                    fontSize = 12.sp,
                                    fontWeight = if (isVipSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            alwaysShowLabel = true,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = UniQashGoldLight,
                                selectedTextColor = UniQashGoldLight,
                                indicatorColor = UniQashBluePrimary,
                                unselectedIconColor = Color.White.copy(alpha = 0.85f),
                                unselectedTextColor = Color.White.copy(alpha = 0.85f)
                            ),
                            modifier = Modifier.testTag("nav_vip_tips")
                        )

                        // 3. Settings
                        val isSettingsSelected = currentScreen is ScreenDestination.Settings ||
                                currentScreen is ScreenDestination.PrivacyPolicy ||
                                currentScreen is ScreenDestination.About
                        NavigationBarItem(
                            selected = isSettingsSelected,
                            onClick = {
                                currentScreen = ScreenDestination.Settings
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = strings.navSettings,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = strings.navSettings,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSettingsSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            alwaysShowLabel = true,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = UniQashGoldLight,
                                selectedTextColor = UniQashGoldLight,
                                indicatorColor = UniQashBluePrimary,
                                unselectedIconColor = Color.White.copy(alpha = 0.85f),
                                unselectedTextColor = Color.White.copy(alpha = 0.85f)
                            ),
                            modifier = Modifier.testTag("nav_settings")
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (val screen = currentScreen) {
                    is ScreenDestination.FreeTips -> {
                        val freeGroups by viewModel.freeTipsGrouped.collectAsState()
                        FreeTipsScreen(
                            dateGroups = freeGroups,
                            strings = strings,
                            onResultChanged = { id, res -> viewModel.updatePredictionResult(id, res) }
                        )
                    }

                    is ScreenDestination.VipDrawers -> {
                        VipTipsScreen(
                            strings = strings,
                            onOpenVipCategory = { category ->
                                currentScreen = ScreenDestination.VipSubCategory(category)
                            },
                            onOpenHistory = {
                                currentScreen = ScreenDestination.HistoryHub
                            }
                        )
                    }

                    is ScreenDestination.VipSubCategory -> {
                        val activeGroups by viewModel.getActiveVipGrouped(screen.category).collectAsState()
                        VipSubCategoryScreen(
                            category = screen.category,
                            dateGroups = activeGroups,
                            strings = strings,
                            onResultChanged = { id, res -> viewModel.updatePredictionResult(id, res) }
                        )
                    }

                    is ScreenDestination.HistoryHub -> {
                        HistoryVipHubScreen(
                            strings = strings,
                            onSelectCategory = { cat ->
                                currentScreen = ScreenDestination.HistoryCategory(cat)
                            }
                        )
                    }

                    is ScreenDestination.HistoryCategory -> {
                        val historyGroups by viewModel.getHistoryVipGrouped(screen.category).collectAsState()
                        HistoryCategoryDetailScreen(
                            category = screen.category,
                            dateGroups = historyGroups,
                            strings = strings,
                            onResultChanged = { id, res -> viewModel.updatePredictionResult(id, res) }
                        )
                    }

                    is ScreenDestination.Settings -> {
                        SettingsScreen(
                            strings = strings,
                            currentLanguage = currentLanguage,
                            onLanguageSelected = { lang -> viewModel.setLanguage(lang) },
                            onOpenPrivacyPolicy = { currentScreen = ScreenDestination.PrivacyPolicy },
                            onOpenAbout = { currentScreen = ScreenDestination.About }
                        )
                    }

                    is ScreenDestination.PrivacyPolicy -> {
                        PrivacyPolicyScreen(strings = strings)
                    }

                    is ScreenDestination.About -> {
                        AboutScreen(strings = strings)
                    }
                }
            }
        }
    }
}
