package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.model.AppLanguage
import com.example.model.LanguageStrings
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read app name from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("uniQash Predictions", appName)
  }

  @Test
  fun `verify arabic language rtl support`() {
    val arabic = AppLanguage.AR
    assertTrue(arabic.isRtl)
    val arabicStrings = LanguageStrings(arabic)
    assertEquals("توقعات uniQash", arabicStrings.appName)
  }
}
