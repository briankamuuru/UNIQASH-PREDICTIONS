package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppLanguage
import com.example.model.LanguageStrings
import com.example.ui.theme.UniQashBlueDark
import com.example.ui.theme.UniQashBluePrimary
import com.example.ui.theme.UniQashTextPrimary
import com.example.ui.theme.UniQashTextSecondary

@Composable
fun PrivacyPolicyScreen(
    strings: LanguageStrings,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(18.dp)
            .testTag("privacy_policy_screen")
    ) {
        PolicySectionHeader("1. Purpose of the Application")
        PolicyParagraph(
            "uniQash Predictions is an informational football sports tips and prediction companion. " +
            "The application provides match schedules, prediction markets, and daily accumulated odds calculations " +
            "for informational and entertainment purposes only."
        )

        PolicySectionHeader("2. Data Collection and Processing")
        PolicyParagraph(
            "uniQash Predictions does not collect, track, or share any personally identifiable information (PII). " +
            "You are not required to create an account, log in, or provide an email address, phone number, or name to use this application."
        )

        PolicySectionHeader("3. Local Storage vs. External Transmission")
        PolicyParagraph(
            "All prediction state changes, result declarations (Pending, Won, Lost), and selected language preferences " +
            "are stored strictly on your local device using an encrypted local SQLite database. No personal data or user logs " +
            "are transmitted to external cloud servers, remote databases, or tracking networks."
        )

        PolicySectionHeader("4. Analytics and Third-Party Services")
        PolicyParagraph(
            "The current release of uniQash Predictions contains zero third-party advertising SDKs, zero analytics tracking " +
            "frameworks, and zero social media plugins. Your in-app activity remains private to your device."
        )

        PolicySectionHeader("5. No Financial Transactions or Gambling")
        PolicyParagraph(
            "The application does not facilitate real-money gambling, sports betting, or wagering. Furthermore, all VIP categories " +
            "are completely free to access. No payment gateways, credit card processing, or financial transactions take place in the app."
        )

        PolicySectionHeader("6. Children's Privacy")
        PolicyParagraph(
            "We do not knowingly collect personal information from children under the age of 13. Because no personal data is collected from " +
            "any user, the application complies with child online privacy protection principles."
        )

        PolicySectionHeader("7. Data Retention & Deletion")
        PolicyParagraph(
            "Local prediction preferences and history remain on your device. You can clear or reset this data at any time via the " +
            "in-app Reset button or through your Android device application settings."
        )

        PolicySectionHeader("8. Policy Updates and Contact")
        PolicyParagraph(
            "Any future updates to this policy will be posted directly within this section. For any privacy-related questions, " +
            "please contact us at support@uniqashpredictions.app."
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun PolicySectionHeader(title: String) {
    Spacer(modifier = Modifier.height(14.dp))
    Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = UniQashBluePrimary
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun PolicyParagraph(content: String) {
    Text(
        text = content,
        fontSize = 13.sp,
        lineHeight = 19.sp,
        fontWeight = FontWeight.Normal,
        color = UniQashTextSecondary
    )
}
