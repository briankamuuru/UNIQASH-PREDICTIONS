package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LanguageStrings
import com.example.model.PredictionEntity
import com.example.model.PredictionResult
import com.example.ui.theme.UniQashBgClean
import com.example.ui.theme.UniQashBlueDark
import com.example.ui.theme.UniQashBlueLight
import com.example.ui.theme.UniQashBluePrimary
import com.example.ui.theme.UniQashBorderBlue
import com.example.ui.theme.UniQashBrightGreen
import com.example.ui.theme.UniQashCardBgWhite
import com.example.ui.theme.UniQashGold
import com.example.ui.theme.UniQashGoldDark
import com.example.ui.theme.UniQashLostOddsColor
import com.example.ui.theme.UniQashLostRedBg
import com.example.ui.theme.UniQashLostRedBorder
import com.example.ui.theme.UniQashLostRedText
import com.example.ui.theme.UniQashTextMuted
import com.example.ui.theme.UniQashTextPrimary
import com.example.ui.theme.UniQashWonGreenBg
import com.example.ui.theme.UniQashWonGreenBorder
import com.example.ui.theme.UniQashWonGreenText
import com.example.ui.theme.UniQashWonOddsColor
import java.util.Locale

@Composable
fun PredictionCard(
    prediction: PredictionEntity,
    strings: LanguageStrings,
    onResultChanged: (PredictionResult) -> Unit,
    modifier: Modifier = Modifier
) {
    var showResultPicker by remember { mutableStateOf(false) }

    // Card styling depending on visual result state
    val cardBackground: Color
    val cardBorder: Color
    val teamTextColor: Color
    val oddsBadgeBg: Color
    val oddsTextColor: Color
    val isTicked: Boolean

    when {
        prediction.isWon -> {
            cardBackground = UniQashWonGreenBg
            cardBorder = UniQashWonGreenBorder
            teamTextColor = UniQashWonGreenText
            oddsBadgeBg = Color(0xFFC8E6C9)
            oddsTextColor = UniQashWonOddsColor
            isTicked = true
        }
        prediction.isLost -> {
            cardBackground = UniQashLostRedBg
            cardBorder = UniQashLostRedBorder
            teamTextColor = UniQashLostRedText
            oddsBadgeBg = Color(0xFFFFCDD2)
            oddsTextColor = UniQashLostOddsColor
            isTicked = true
        }
        else -> { // Pending
            cardBackground = UniQashCardBgWhite
            cardBorder = UniQashBorderBlue.copy(alpha = 0.5f)
            teamTextColor = UniQashTextPrimary
            oddsBadgeBg = UniQashBluePrimary.copy(alpha = 0.08f)
            oddsTextColor = UniQashBluePrimary
            isTicked = false
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("prediction_card_${prediction.id}")
            .clickable { showResultPicker = true },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackground),
        border = BorderStroke(1.2.dp, cardBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            // Header: League Title (Bright Green) & Kickoff Time
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = prediction.league,
                    color = UniQashBrightGreen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )

                if (prediction.kickoffTime.isNotEmpty()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = "Kickoff Time",
                            tint = if (prediction.isWon) UniQashWonGreenText.copy(alpha = 0.7f)
                            else if (prediction.isLost) UniQashLostRedText.copy(alpha = 0.7f)
                            else UniQashTextMuted,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = prediction.kickoffTime,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (prediction.isWon) UniQashWonGreenText.copy(alpha = 0.8f)
                            else if (prediction.isLost) UniQashLostRedText.copy(alpha = 0.8f)
                            else UniQashTextMuted
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Main Row: Teams vs Teams and Square Selection Box
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Teams
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${prediction.homeTeam} vs ${prediction.awayTeam}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = teamTextColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Prediction / Market
                    Text(
                        text = prediction.market,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (prediction.isWon) UniQashWonGreenText.copy(alpha = 0.9f)
                        else if (prediction.isLost) UniQashLostRedText.copy(alpha = 0.9f)
                        else UniQashBlueDark
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                // Right column: Odds Badge and Small Square Selection Box
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    // Odds Box
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(oddsBadgeBg)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        val oddsStr = String.format(Locale.US, "%.2f", prediction.odds)
                        Text(
                            text = oddsStr,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = oddsTextColor
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    // Square Selection Box:
                    // Pending: white fill with blue outline, unticked.
                    // Won/Lost: clearly ticked.
                    SquareSelectionBox(
                        isTicked = isTicked,
                        isWon = prediction.isWon,
                        isLost = prediction.isLost,
                        onClick = { showResultPicker = true }
                    )
                }
            }
        }
    }

    if (showResultPicker) {
        ResultPickerDialog(
            prediction = prediction,
            strings = strings,
            onDismiss = { showResultPicker = false },
            onSelectResult = { result ->
                onResultChanged(result)
                showResultPicker = false
            }
        )
    }
}

@Composable
fun SquareSelectionBox(
    isTicked: Boolean,
    isWon: Boolean,
    isLost: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val boxBg: Color
    val boxBorder: Color
    val checkmarkColor: Color

    when {
        isWon -> {
            boxBg = UniQashWonGreenBorder
            boxBorder = UniQashWonGreenBorder
            checkmarkColor = Color.White
        }
        isLost -> {
            boxBg = UniQashLostRedBorder
            boxBorder = UniQashLostRedBorder
            checkmarkColor = Color.White
        }
        else -> {
            boxBg = Color.White
            boxBorder = UniQashBluePrimary
            checkmarkColor = Color.Transparent
        }
    }

    Box(
        modifier = modifier
            .size(24.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(boxBg)
            .border(1.5.dp, boxBorder, RoundedCornerShape(4.dp))
            .clickable { onClick() }
            .testTag("selection_box"),
        contentAlignment = Alignment.Center
    ) {
        if (isTicked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = checkmarkColor,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun ResultPickerDialog(
    prediction: PredictionEntity,
    strings: LanguageStrings,
    onDismiss: () -> Unit,
    onSelectResult: (PredictionResult) -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = strings.resultActionTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = UniQashBlueDark
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "${prediction.homeTeam} vs ${prediction.awayTeam}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = UniQashTextPrimary
                )
                Text(
                    text = "${prediction.market} • ${String.format(Locale.US, "%.2f", prediction.odds)}",
                    fontSize = 12.sp,
                    color = UniQashBrightGreen,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Pending Button
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectResult(PredictionResult.PENDING) },
                    colors = CardDefaults.cardColors(containerColor = UniQashCardBgWhite),
                    border = BorderStroke(1.dp, UniQashBorderBlue)
                ) {
                    Text(
                        text = strings.actionPending,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = UniQashBluePrimary,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                // Won Button
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectResult(PredictionResult.WON) },
                    colors = CardDefaults.cardColors(containerColor = UniQashWonGreenBg),
                    border = BorderStroke(1.dp, UniQashWonGreenBorder)
                ) {
                    Text(
                        text = strings.actionWon,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = UniQashWonGreenText,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                // Lost Button
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectResult(PredictionResult.LOST) },
                    colors = CardDefaults.cardColors(containerColor = UniQashLostRedBg),
                    border = BorderStroke(1.dp, UniQashLostRedBorder)
                ) {
                    Text(
                        text = strings.actionLost,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = UniQashLostRedText,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                Text(text = strings.cancel, color = UniQashTextMuted)
            }
        }
    )
}
