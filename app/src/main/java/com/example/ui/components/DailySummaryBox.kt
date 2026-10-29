package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LanguageStrings
import com.example.ui.theme.UniQashBlueDark
import com.example.ui.theme.UniQashBluePrimary
import com.example.ui.theme.UniQashBorderBlue
import com.example.ui.theme.UniQashGold
import com.example.ui.theme.UniQashGoldDark
import java.util.Locale

@Composable
fun DailySummaryBox(
    gamesCount: Int,
    accumulatedOdds: Double,
    strings: LanguageStrings,
    modifier: Modifier = Modifier
) {
    val oddsFormatted = String.format(Locale.US, "%.2f", accumulatedOdds)
    val text = strings.dailySummaryText(gamesCount, oddsFormatted)

    // Tasteful blue outline with blue-to-gold gradient fill
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(
            UniQashBluePrimary.copy(alpha = 0.08f),
            UniQashGold.copy(alpha = 0.12f)
        )
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(1.2.dp, UniQashBorderBlue)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradientBrush)
                .padding(horizontal = 12.dp, vertical = 9.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = UniQashBlueDark,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                letterSpacing = 0.4.sp
            )
        }
    }
}
