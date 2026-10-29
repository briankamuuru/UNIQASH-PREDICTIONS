package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.DateGroupedPredictions
import com.example.model.LanguageStrings
import com.example.model.PredictionResult
import com.example.model.VipCategory
import com.example.ui.components.DailySummaryBox
import com.example.ui.components.PredictionCard
import com.example.ui.theme.UniQashBgClean
import com.example.ui.theme.UniQashBlueDark
import com.example.ui.theme.UniQashBluePrimary
import com.example.ui.theme.UniQashBorderBlue
import com.example.ui.theme.UniQashBrightGreen
import com.example.ui.theme.UniQashGold
import com.example.ui.theme.UniQashTextMuted

@Composable
fun HistoryVipHubScreen(
    strings: LanguageStrings,
    onSelectCategory: (VipCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(UniQashBgClean)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        HistoryCategoryCard(
            title = strings.historyTwoPlus,
            icon = Icons.AutoMirrored.Filled.TrendingUp,
            onClick = { onSelectCategory(VipCategory.TWO_PLUS) },
            testTag = "hist_cat_2_plus"
        )
        HistoryCategoryCard(
            title = strings.historyThreePlus,
            icon = Icons.Default.Star,
            onClick = { onSelectCategory(VipCategory.THREE_PLUS) },
            testTag = "hist_cat_3_plus"
        )
        HistoryCategoryCard(
            title = strings.historyFivePlus,
            icon = Icons.Default.ElectricBolt,
            onClick = { onSelectCategory(VipCategory.FIVE_PLUS) },
            testTag = "hist_cat_5_plus"
        )
        HistoryCategoryCard(
            title = strings.historySpecial,
            icon = Icons.Default.WorkspacePremium,
            onClick = { onSelectCategory(VipCategory.SPECIAL) },
            testTag = "hist_cat_special"
        )
    }
}

@Composable
private fun HistoryCategoryCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    testTag: String
) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(
            UniQashBluePrimary.copy(alpha = 0.10f),
            UniQashGold.copy(alpha = 0.18f)
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .testTag(testTag)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(1.2.dp, UniQashBorderBlue),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBrush)
                .padding(horizontal = 14.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = UniQashBluePrimary,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = UniQashBlueDark
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Open",
                    tint = UniQashBluePrimary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun HistoryCategoryDetailScreen(
    category: VipCategory,
    dateGroups: List<DateGroupedPredictions>,
    strings: LanguageStrings,
    onResultChanged: (String, PredictionResult) -> Unit,
    modifier: Modifier = Modifier
) {
    if (dateGroups.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(UniQashBgClean)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = strings.emptyHistory,
                color = UniQashTextMuted,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(UniQashBgClean)
                .testTag("history_list_${category.storageKey}"),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            dateGroups.forEach { dateGroup ->
                // Date Heading (Bright Green)
                item(key = "hist_header_${dateGroup.matchDate}") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 4.dp)
                    ) {
                        Text(
                            text = dateGroup.displayDate.uppercase(),
                            color = UniQashBrightGreen,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 13.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                }

                // Historical Predictions
                items(
                    items = dateGroup.predictions,
                    key = { it.id }
                ) { prediction ->
                    PredictionCard(
                        prediction = prediction,
                        strings = strings,
                        onResultChanged = { result ->
                            onResultChanged(prediction.id, result)
                        }
                    )
                }

                // Daily Summary Drawer for history
                item(key = "hist_summary_${dateGroup.matchDate}") {
                    DailySummaryBox(
                        gamesCount = dateGroup.totalGames,
                        accumulatedOdds = dateGroup.accumulatedOdds,
                        strings = strings
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }
}
