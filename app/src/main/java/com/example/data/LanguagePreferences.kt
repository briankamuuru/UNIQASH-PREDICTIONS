package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.model.AppLanguage

class LanguagePreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("uniqash_language_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_LANGUAGE = "selected_language"
    }

    fun getLanguage(): AppLanguage {
        val code = prefs.getString(KEY_LANGUAGE, AppLanguage.EN.code) ?: AppLanguage.EN.code
        return AppLanguage.fromCode(code)
    }

    fun setLanguage(language: AppLanguage) {
        prefs.edit().putString(KEY_LANGUAGE, language.code).apply()
    }
}
