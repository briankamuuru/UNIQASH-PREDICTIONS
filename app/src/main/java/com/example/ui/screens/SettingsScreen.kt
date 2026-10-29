package com.example.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.BuildConfig
import com.example.model.AppLanguage
import com.example.model.LanguageStrings
import com.example.ui.theme.UniQashBlueDark
import com.example.ui.theme.UniQashBluePrimary
import com.example.ui.theme.UniQashBorderSubtle
import com.example.ui.theme.UniQashCardBgWhite
import com.example.ui.theme.UniQashTextMuted
import com.example.ui.theme.UniQashTextPrimary

@Composable
fun SettingsScreen(
    strings: LanguageStrings,
    currentLanguage: AppLanguage,
    onLanguageSelected: (AppLanguage) -> Unit,
    onOpenPrivacyPolicy: () -> Unit,
    onOpenAbout: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showLanguageDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 12.dp)
            .testTag("settings_screen")
    ) {
        // Item 1: Language
        SettingsListItem(
            title = strings.itemLanguage,
            trailingText = currentLanguage.displayName,
            icon = Icons.Default.Language,
            onClick = { showLanguageDialog = true },
            testTag = "settings_item_language"
        )

        HorizontalDivider(
            color = UniQashBorderSubtle.copy(alpha = 0.5f),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Item 2: Version
        SettingsListItem(
            title = strings.itemVersion,
            trailingText = BuildConfig.VERSION_NAME,
            icon = Icons.Default.Info,
            onClick = { /* Display only */ },
            testTag = "settings_item_version",
            showArrow = false
        )

        HorizontalDivider(
            color = UniQashBorderSubtle.copy(alpha = 0.5f),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Item 3: Privacy Policy
        SettingsListItem(
            title = strings.itemPrivacyPolicy,
            icon = Icons.Default.Security,
            onClick = onOpenPrivacyPolicy,
            testTag = "settings_item_privacy_policy"
        )

        HorizontalDivider(
            color = UniQashBorderSubtle.copy(alpha = 0.5f),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Item 4: About
        SettingsListItem(
            title = strings.itemAbout,
            icon = Icons.Default.Policy,
            onClick = onOpenAbout,
            testTag = "settings_item_about"
        )
    }

    if (showLanguageDialog) {
        LanguageSelectionDialog(
            currentLanguage = currentLanguage,
            strings = strings,
            onDismiss = { showLanguageDialog = false },
            onSelect = { lang ->
                onLanguageSelected(lang)
                showLanguageDialog = false
            }
        )
    }
}

@Composable
private fun SettingsListItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    testTag: String,
    trailingText: String? = null,
    showArrow: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag(testTag)
            .padding(horizontal = 16.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = UniQashBluePrimary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = UniQashTextPrimary
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (trailingText != null) {
                Text(
                    text = trailingText,
                    fontSize = 14.sp,
                    color = UniQashTextMuted,
                    fontWeight = FontWeight.Normal
                )
                if (showArrow) {
                    Spacer(modifier = Modifier.width(6.dp))
                }
            }

            if (showArrow) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = UniQashTextMuted.copy(alpha = 0.6f),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun LanguageSelectionDialog(
    currentLanguage: AppLanguage,
    strings: LanguageStrings,
    onDismiss: () -> Unit,
    onSelect: (AppLanguage) -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = strings.selectLanguage,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = UniQashBlueDark
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                AppLanguage.entries.forEach { lang ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(lang) }
                            .padding(vertical = 8.dp, horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (lang == currentLanguage),
                            onClick = { onSelect(lang) },
                            colors = RadioButtonDefaults.colors(selectedColor = UniQashBluePrimary)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = lang.displayName,
                            fontSize = 15.sp,
                            fontWeight = if (lang == currentLanguage) FontWeight.Bold else FontWeight.Normal,
                            color = if (lang == currentLanguage) UniQashBluePrimary else UniQashTextPrimary
                        )
                    }
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
