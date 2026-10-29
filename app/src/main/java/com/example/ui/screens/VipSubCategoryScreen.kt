package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.ui.theme.UniQashBrightGreen
import com.example.ui.theme.UniQashTextMuted

@Composable
fun VipSubCategoryScreen(
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
                text = strings.emptyPredictions,
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
                .testTag("vip_subcategory_list_${category.storageKey}"),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            dateGroups.forEach { dateGroup ->
                // Date Heading (Bright Green)
                item(key = "vip_header_${dateGroup.matchDate}") {
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

                // Predictions for this date
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

                // Daily Summary Drawer
                item(key = "vip_summary_${dateGroup.matchDate}") {
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
