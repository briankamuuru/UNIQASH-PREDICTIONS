package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.History
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LanguageStrings
import com.example.model.VipCategory
import com.example.ui.theme.UniQashBgClean
import com.example.ui.theme.UniQashBlueDark
import com.example.ui.theme.UniQashBluePrimary
import com.example.ui.theme.UniQashBorderBlue
import com.example.ui.theme.UniQashGold
import com.example.ui.theme.UniQashGoldDark

@Composable
fun VipTipsScreen(
    strings: LanguageStrings,
    onOpenVipCategory: (VipCategory) -> Unit,
    onOpenHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(UniQashBgClean)
            .verticalScroll(scrollState)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Row 1: 2+ Tips VIP | 3+ Tips VIP
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            VipDrawerItem(
                title = strings.vipTwoPlus,
                icon = Icons.AutoMirrored.Filled.TrendingUp,
                onClick = { onOpenVipCategory(VipCategory.TWO_PLUS) },
                testTag = "vip_drawer_2_plus",
                modifier = Modifier.weight(1f)
            )
            VipDrawerItem(
                title = strings.vipThreePlus,
                icon = Icons.Default.Star,
                onClick = { onOpenVipCategory(VipCategory.THREE_PLUS) },
                testTag = "vip_drawer_3_plus",
                modifier = Modifier.weight(1f)
            )
        }

        // Row 2: 5+ Tips VIP | Special VIP
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            VipDrawerItem(
                title = strings.vipFivePlus,
                icon = Icons.Default.ElectricBolt,
                onClick = { onOpenVipCategory(VipCategory.FIVE_PLUS) },
                testTag = "vip_drawer_5_plus",
                modifier = Modifier.weight(1f)
            )
            VipDrawerItem(
                title = strings.vipSpecial,
                icon = Icons.Default.WorkspacePremium,
                onClick = { onOpenVipCategory(VipCategory.SPECIAL) },
                testTag = "vip_drawer_special",
                modifier = Modifier.weight(1f)
            )
        }

        // Row 3: History VIP (occupying one grid position and neatly aligned)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            VipDrawerItem(
                title = strings.vipHistory,
                icon = Icons.Default.History,
                onClick = onOpenHistory,
                testTag = "vip_drawer_history",
                modifier = Modifier.weight(1f)
            )
            // Empty spacer to keep History VIP occupying exactly one grid position
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun VipDrawerItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    testTag: String,
    modifier: Modifier = Modifier
) {
    // Tasteful blue-to-gold gradient fill
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            UniQashBluePrimary.copy(alpha = 0.12f),
            UniQashGold.copy(alpha = 0.22f)
        )
    )

    Card(
        modifier = modifier
            .height(86.dp)
            .testTag(testTag)
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.Transparent),
        border = BorderStroke(1.3.dp, UniQashBorderBlue),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBrush)
                .padding(horizontal = 12.dp, vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = UniQashBluePrimary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = UniQashBlueDark,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}
