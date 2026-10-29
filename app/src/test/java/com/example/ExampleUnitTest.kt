package com.example

import com.example.model.MainSection
import com.example.model.PredictionEntity
import com.example.model.PredictionResult
import com.example.viewmodel.PredictionsViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {

  @Test
  fun testAccumulatedOddsMultiplication() {
    // Game 1: 1.50, Game 2: 2.00, Game 3: 1.80 => 1.50 * 2.00 * 1.80 = 5.40
    val predictions = listOf(
      PredictionEntity(
        id = "1",
        homeTeam = "Team A",
        awayTeam = "Team B",
        league = "League 1",
        market = "Home Win",
        odds = 1.50,
        matchDate = "2026-10-12",
        displayDate = "Monday, 12 Oct 2026",
        kickoffTime = "20:00",
        section = MainSection.FREE.name,
        vipCategory = null,
        resultState = PredictionResult.PENDING.name
      ),
      PredictionEntity(
        id = "2",
        homeTeam = "Team C",
        awayTeam = "Team D",
        league = "League 1",
        market = "Over 2.5",
        odds = 2.00,
        matchDate = "2026-10-12",
        displayDate = "Monday, 12 Oct 2026",
        kickoffTime = "21:00",
        section = MainSection.FREE.name,
        vipCategory = null,
        resultState = PredictionResult.PENDING.name
      ),
      PredictionEntity(
        id = "3",
        homeTeam = "Team E",
        awayTeam = "Team F",
        league = "League 1",
        market = "BTTS",
        odds = 1.80,
        matchDate = "2026-10-12",
        displayDate = "Monday, 12 Oct 2026",
        kickoffTime = "22:00",
        section = MainSection.FREE.name,
        vipCategory = null,
        resultState = PredictionResult.PENDING.name
      )
    )

    val grouped = PredictionsViewModel.groupPredictionsByDate(predictions)
    assertEquals(1, grouped.size)
    assertEquals(3, grouped[0].totalGames)
    assertEquals(5.40, grouped[0].accumulatedOdds, 0.001)
  }
}
