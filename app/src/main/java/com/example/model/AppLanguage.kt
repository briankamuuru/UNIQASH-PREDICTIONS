package com.example.model

enum class AppLanguage(val code: String, val displayName: String, val isRtl: Boolean) {
    EN("en", "English", false),
    AR("ar", "العربية", true),
    ES("es", "Español", false),
    DE("de", "Deutsch", false);

    companion object {
        fun fromCode(code: String): AppLanguage {
            return entries.find { it.code.equals(code, ignoreCase = true) } ?: EN
        }
    }
}

class LanguageStrings(val language: AppLanguage) {
    val appName: String = when (language) {
        AppLanguage.EN -> "uniQash Predictions"
        AppLanguage.AR -> "توقعات uniQash"
        AppLanguage.ES -> "uniQash Pronósticos"
        AppLanguage.DE -> "uniQash Vorhersagen"
    }

    val navFreeTips: String = when (language) {
        AppLanguage.EN -> "Free Tips"
        AppLanguage.AR -> "نصائح مجانية"
        AppLanguage.ES -> "Gratis"
        AppLanguage.DE -> "Gratis-Tipps"
    }

    val navVipTips: String = when (language) {
        AppLanguage.EN -> "VIP Tips"
        AppLanguage.AR -> "نصائح VIP"
        AppLanguage.ES -> "VIP Tips"
        AppLanguage.DE -> "VIP-Tipps"
    }

    val navSettings: String = when (language) {
        AppLanguage.EN -> "Settings"
        AppLanguage.AR -> "الإعدادات"
        AppLanguage.ES -> "Ajustes"
        AppLanguage.DE -> "Einstellungen"
    }

    val freeTipsTitle: String = when (language) {
        AppLanguage.EN -> "Free Predictions"
        AppLanguage.AR -> "التوقعات المجانية"
        AppLanguage.ES -> "Pronósticos Gratuitos"
        AppLanguage.DE -> "Kostenlose Vorhersagen"
    }

    val vipTipsTitle: String = when (language) {
        AppLanguage.EN -> "VIP Drawers"
        AppLanguage.AR -> "أقسام VIP"
        AppLanguage.ES -> "Secciones VIP"
        AppLanguage.DE -> "VIP-Bereiche"
    }

    val vipTwoPlus: String = when (language) {
        AppLanguage.EN -> "2+ Tips VIP"
        AppLanguage.AR -> "2+ نصائح VIP"
        AppLanguage.ES -> "2+ Tips VIP"
        AppLanguage.DE -> "2+ Tipps VIP"
    }

    val vipThreePlus: String = when (language) {
        AppLanguage.EN -> "3+ Tips VIP"
        AppLanguage.AR -> "3+ نصائح VIP"
        AppLanguage.ES -> "3+ Tips VIP"
        AppLanguage.DE -> "3+ Tipps VIP"
    }

    val vipFivePlus: String = when (language) {
        AppLanguage.EN -> "5+ Tips VIP"
        AppLanguage.AR -> "5+ نصائح VIP"
        AppLanguage.ES -> "5+ Tips VIP"
        AppLanguage.DE -> "5+ Tipps VIP"
    }

    val vipSpecial: String = when (language) {
        AppLanguage.EN -> "Special VIP"
        AppLanguage.AR -> "VIP المميز"
        AppLanguage.ES -> "VIP Especial"
        AppLanguage.DE -> "Spezial-VIP"
    }

    val vipHistory: String = when (language) {
        AppLanguage.EN -> "History VIP"
        AppLanguage.AR -> "سجل VIP"
        AppLanguage.ES -> "Historial VIP"
        AppLanguage.DE -> "VIP-Verlauf"
    }

    val vipHistoryTitle: String = when (language) {
        AppLanguage.EN -> "VIP History Archives"
        AppLanguage.AR -> "أرشيف سجل VIP"
        AppLanguage.ES -> "Archivo Histórico VIP"
        AppLanguage.DE -> "VIP-Verlaufsarchiv"
    }

    val historyTwoPlus: String = when (language) {
        AppLanguage.EN -> "2+ Tips VIP History"
        AppLanguage.AR -> "سجل 2+ نصائح VIP"
        AppLanguage.ES -> "Historial 2+ Tips VIP"
        AppLanguage.DE -> "Verlauf 2+ Tipps VIP"
    }

    val historyThreePlus: String = when (language) {
        AppLanguage.EN -> "3+ Tips VIP History"
        AppLanguage.AR -> "سجل 3+ نصائح VIP"
        AppLanguage.ES -> "Historial 3+ Tips VIP"
        AppLanguage.DE -> "Verlauf 3+ Tipps VIP"
    }

    val historyFivePlus: String = when (language) {
        AppLanguage.EN -> "5+ Tips VIP History"
        AppLanguage.AR -> "سجل 5+ نصائح VIP"
        AppLanguage.ES -> "Historial 5+ Tips VIP"
        AppLanguage.DE -> "Verlauf 5+ Tipps VIP"
    }

    val historySpecial: String = when (language) {
        AppLanguage.EN -> "Special VIP History"
        AppLanguage.AR -> "سجل VIP المميز"
        AppLanguage.ES -> "Historial VIP Especial"
        AppLanguage.DE -> "Verlauf Spezial-VIP"
    }

    fun dailySummaryText(games: Int, oddsFormatted: String): String {
        return when (language) {
            AppLanguage.EN -> "DAILY SUMMARY | $games GAMES | ACCUMULATED ODDS: $oddsFormatted"
            AppLanguage.AR -> "الملخص اليومي | $games مباريات | إجمالي الاحتمالات: $oddsFormatted"
            AppLanguage.ES -> "RESUMEN DIARIO | $games PARTIDOS | CUOTAS ACUMULADAS: $oddsFormatted"
            AppLanguage.DE -> "TAGESÜBERSICHT | $games SPIELE | GESAMTQUOTE: $oddsFormatted"
        }
    }

    val settingsTitle: String = when (language) {
        AppLanguage.EN -> "Settings"
        AppLanguage.AR -> "الإعدادات"
        AppLanguage.ES -> "Ajustes"
        AppLanguage.DE -> "Einstellungen"
    }

    val itemLanguage: String = when (language) {
        AppLanguage.EN -> "Language"
        AppLanguage.AR -> "اللغة"
        AppLanguage.ES -> "Idioma"
        AppLanguage.DE -> "Sprache"
    }

    val itemVersion: String = when (language) {
        AppLanguage.EN -> "Version"
        AppLanguage.AR -> "الإصدار"
        AppLanguage.ES -> "Versión"
        AppLanguage.DE -> "Version"
    }

    val itemPrivacyPolicy: String = when (language) {
        AppLanguage.EN -> "Privacy Policy"
        AppLanguage.AR -> "سياسة الخصوصية"
        AppLanguage.ES -> "Política de Privacidad"
        AppLanguage.DE -> "Datenschutzrichtlinie"
    }

    val itemAbout: String = when (language) {
        AppLanguage.EN -> "About"
        AppLanguage.AR -> "حول التطبيق"
        AppLanguage.ES -> "Acerca de"
        AppLanguage.DE -> "Über uns"
    }

    val emptyPredictions: String = when (language) {
        AppLanguage.EN -> "No predictions available for this category yet."
        AppLanguage.AR -> "لا توجد توقعات متاحة لهذا القسم حالياً."
        AppLanguage.ES -> "No hay pronósticos disponibles en esta categoría todavía."
        AppLanguage.DE -> "Derzeit sind für diese Kategorie keine Vorhersagen verfügbar."
    }

    val emptyHistory: String = when (language) {
        AppLanguage.EN -> "No completed match history in this category yet."
        AppLanguage.AR -> "لا يوجد سجل مباريات منتهية في هذا القسم بعد."
        AppLanguage.ES -> "No hay historial de partidos finalizados en esta categoría."
        AppLanguage.DE -> "In dieser Kategorie ist noch kein Spielverlauf vorhanden."
    }

    val back: String = when (language) {
        AppLanguage.EN -> "Back"
        AppLanguage.AR -> "رجوع"
        AppLanguage.ES -> "Atrás"
        AppLanguage.DE -> "Zurück"
    }

    val selectLanguage: String = when (language) {
        AppLanguage.EN -> "Select Language"
        AppLanguage.AR -> "اختر اللغة"
        AppLanguage.ES -> "Seleccionar Idioma"
        AppLanguage.DE -> "Sprache auswählen"
    }

    val resultActionTitle: String = when (language) {
        AppLanguage.EN -> "Update Match Result"
        AppLanguage.AR -> "تحديث نتيجة المباراة"
        AppLanguage.ES -> "Actualizar Resultado"
        AppLanguage.DE -> "Spielergebnis aktualisieren"
    }

    val actionPending: String = when (language) {
        AppLanguage.EN -> "Set as Pending (Unticked)"
        AppLanguage.AR -> "تعيين كقيد الانتظار (غير محدد)"
        AppLanguage.ES -> "Marcar como Pendiente"
        AppLanguage.DE -> "Als ausstehend festlegen"
    }

    val actionWon: String = when (language) {
        AppLanguage.EN -> "Set as Won (Green & Ticked)"
        AppLanguage.AR -> "تعيين كفوز (أخضر ومحدد)"
        AppLanguage.ES -> "Marcar como Ganado (Verde)"
        AppLanguage.DE -> "Als gewonnen festlegen (Grün)"
    }

    val actionLost: String = when (language) {
        AppLanguage.EN -> "Set as Lost (Red & Ticked)"
        AppLanguage.AR -> "تعيين كخسارة (أحمر ومحدد)"
        AppLanguage.ES -> "Marcar como Perdido (Rojo)"
        AppLanguage.DE -> "Als verloren festlegen (Rot)"
    }

    val cancel: String = when (language) {
        AppLanguage.EN -> "Cancel"
        AppLanguage.AR -> "إلغاء"
        AppLanguage.ES -> "Cancelar"
        AppLanguage.DE -> "Abbrechen"
    }

    val resetDemo: String = when (language) {
        AppLanguage.EN -> "Reset Demo Fixtures"
        AppLanguage.AR -> "إعادة ضبط المباريات التجريبية"
        AppLanguage.ES -> "Restablecer Partidos"
        AppLanguage.DE -> "Demodaten zurücksetzen"
    }
}
