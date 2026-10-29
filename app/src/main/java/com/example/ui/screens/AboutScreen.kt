package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.BuildConfig
import com.example.R
import com.example.model.LanguageStrings
import com.example.ui.theme.UniQashBlueDark
import com.example.ui.theme.UniQashBluePrimary
import com.example.ui.theme.UniQashBorderBlue
import com.example.ui.theme.UniQashBrightGreen
import com.example.ui.theme.UniQashGold
import com.example.ui.theme.UniQashTextMuted
import com.example.ui.theme.UniQashTextPrimary
import com.example.ui.theme.UniQashTextSecondary

@Composable
fun AboutScreen(
    strings: LanguageStrings,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // App Logo
        Card(
            modifier = Modifier
                .size(110.dp)
                .testTag("about_logo"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            border = androidx.compose.foundation.BorderStroke(2.dp, UniQashGold)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_img),
                contentDescription = "uniQash Logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "uniQash Predictions",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = UniQashBluePrimary
        )

        Text(
            text = "Version ${BuildConfig.VERSION_NAME}",
            fontSize = 12.sp,
            color = UniQashTextMuted
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "uniQash Predictions is a sports statistics and football prediction companion that organizes curated football selections by category and date.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = UniQashTextPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Core Features:",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = UniQashBlueDark,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp))

        FeatureBullet("• Free Tips: Chronologically grouped football predictions across major leagues.")
        FeatureBullet("• VIP Tips Categories: Specialized selections including 2+ Tips VIP, 3+ Tips VIP, 5+ Tips VIP, and Special VIP.")
        FeatureBullet("• Daily Accumulated Odds: Exact mathematical multiplication of individual decimal odds per date group.")
        FeatureBullet("• VIP History: Independent archives preserving settled results without duplication.")

        Spacer(modifier = Modifier.height(20.dp))

        // Important Disclaimer Box
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("about_disclaimer_card"),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = UniQashBluePrimary.copy(alpha = 0.05f)),
            border = androidx.compose.foundation.BorderStroke(1.dp, UniQashBorderBlue.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "Important Notice & Disclaimer",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = UniQashBlueDark
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Football match outcomes are inherently uncertain. Statistical tips and predictions are analytical opinions, not guarantees of winning. " +
                            "All accumulated odds presented are purely mathematical calculations (multiplying individual decimal odds). " +
                            "This application does not offer real-money gambling or wagering services.",
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = UniQashTextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun FeatureBullet(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        lineHeight = 19.sp,
        color = UniQashTextSecondary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
    )
}
