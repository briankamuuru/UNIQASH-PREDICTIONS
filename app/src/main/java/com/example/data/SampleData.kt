package com.example.data

import com.example.model.MainSection
import com.example.model.PredictionEntity
import com.example.model.PredictionResult
import com.example.model.VipCategory

object SampleData {
    fun getInitialPredictions(): List<PredictionEntity> {
        return listOf(
            // ================= FREE TIPS =================
            // Date 1: 2026-10-12
            PredictionEntity(
                id = "free-1",
                homeTeam = "Arsenal",
                awayTeam = "Chelsea",
                league = "English Premier League",
                market = "Both Teams to Score (BTTS)",
                odds = 1.78,
                matchDate = "2026-10-12",
                displayDate = "Monday, 12 Oct 2026",
                kickoffTime = "20:00",
                section = MainSection.FREE.name,
                vipCategory = null,
                resultState = PredictionResult.PENDING.name
            ),
            PredictionEntity(
                id = "free-2",
                homeTeam = "Real Madrid",
                awayTeam = "Villarreal",
                league = "Spanish La Liga",
                market = "Home Win & Over 1.5 Goals",
                odds = 1.62,
                matchDate = "2026-10-12",
                displayDate = "Monday, 12 Oct 2026",
                kickoffTime = "21:00",
                section = MainSection.FREE.name,
                vipCategory = null,
                resultState = PredictionResult.PENDING.name
            ),
            PredictionEntity(
                id = "free-3",
                homeTeam = "Juventus",
                awayTeam = "Lazio",
                league = "Italian Serie A",
                market = "Under 2.5 Goals",
                odds = 1.85,
                matchDate = "2026-10-12",
                displayDate = "Monday, 12 Oct 2026",
                kickoffTime = "19:45",
                section = MainSection.FREE.name,
                vipCategory = null,
                resultState = PredictionResult.PENDING.name
            ),

            // Date 2: 2026-10-13
            PredictionEntity(
                id = "free-4",
                homeTeam = "Bayern Munich",
                awayTeam = "Borussia Dortmund",
                league = "German Bundesliga",
                market = "Over 3.5 Goals",
                odds = 1.95,
                matchDate = "2026-10-13",
                displayDate = "Tuesday, 13 Oct 2026",
                kickoffTime = "18:30",
                section = MainSection.FREE.name,
                vipCategory = null,
                resultState = PredictionResult.PENDING.name
            ),
            PredictionEntity(
                id = "free-5",
                homeTeam = "Paris Saint-Germain",
                awayTeam = "Marseille",
                league = "French Ligue 1",
                market = "Home Win (1)",
                odds = 1.55,
                matchDate = "2026-10-13",
                displayDate = "Tuesday, 13 Oct 2026",
                kickoffTime = "20:45",
                section = MainSection.FREE.name,
                vipCategory = null,
                resultState = PredictionResult.PENDING.name
            ),

            // Date 3: Past completed in Free Tips to demonstrate visual won/lost state
            PredictionEntity(
                id = "free-6",
                homeTeam = "Manchester City",
                awayTeam = "Newcastle United",
                league = "English Premier League",
                market = "Home Win & Over 2.5",
                odds = 1.70,
                matchDate = "2026-10-11",
                displayDate = "Sunday, 11 Oct 2026",
                kickoffTime = "16:30",
                section = MainSection.FREE.name,
                vipCategory = null,
                resultState = PredictionResult.WON.name
            ),
            PredictionEntity(
                id = "free-7",
                homeTeam = "AC Milan",
                awayTeam = "Inter Milan",
                league = "Italian Serie A",
                market = "Draw (X)",
                odds = 3.30,
                matchDate = "2026-10-11",
                displayDate = "Sunday, 11 Oct 2026",
                kickoffTime = "19:45",
                section = MainSection.FREE.name,
                vipCategory = null,
                resultState = PredictionResult.LOST.name
            ),

            // ================= 2+ TIPS VIP (Active) =================
            PredictionEntity(
                id = "vip-2plus-1",
                homeTeam = "Liverpool",
                awayTeam = "Aston Villa",
                league = "English Premier League",
                market = "Home Win (1)",
                odds = 1.48,
                matchDate = "2026-10-12",
                displayDate = "Monday, 12 Oct 2026",
                kickoffTime = "20:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.TWO_PLUS.storageKey,
                resultState = PredictionResult.PENDING.name
            ),
            PredictionEntity(
                id = "vip-2plus-2",
                homeTeam = "Barcelona",
                awayTeam = "Sevilla",
                league = "Spanish La Liga",
                market = "Over 2.5 Goals",
                odds = 1.52,
                matchDate = "2026-10-12",
                displayDate = "Monday, 12 Oct 2026",
                kickoffTime = "21:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.TWO_PLUS.storageKey,
                resultState = PredictionResult.PENDING.name
            ),
            PredictionEntity(
                id = "vip-2plus-3",
                homeTeam = "Sporting CP",
                awayTeam = "Braga",
                league = "Portuguese Primeira Liga",
                market = "Home Win & Over 1.5",
                odds = 1.50,
                matchDate = "2026-10-13",
                displayDate = "Tuesday, 13 Oct 2026",
                kickoffTime = "20:15",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.TWO_PLUS.storageKey,
                resultState = PredictionResult.PENDING.name
            ),
            PredictionEntity(
                id = "vip-2plus-4",
                homeTeam = "Feyenoord",
                awayTeam = "Utrecht",
                league = "Dutch Eredivisie",
                market = "Over 2.5 Goals",
                odds = 1.46,
                matchDate = "2026-10-13",
                displayDate = "Tuesday, 13 Oct 2026",
                kickoffTime = "19:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.TWO_PLUS.storageKey,
                resultState = PredictionResult.PENDING.name
            ),

            // ================= 3+ TIPS VIP (Active) =================
            PredictionEntity(
                id = "vip-3plus-1",
                homeTeam = "Bayer Leverkusen",
                awayTeam = "RB Leipzig",
                league = "German Bundesliga",
                market = "Over 2.5 & Both Teams to Score",
                odds = 1.82,
                matchDate = "2026-10-12",
                displayDate = "Monday, 12 Oct 2026",
                kickoffTime = "19:30",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.THREE_PLUS.storageKey,
                resultState = PredictionResult.PENDING.name
            ),
            PredictionEntity(
                id = "vip-3plus-2",
                homeTeam = "Napoli",
                awayTeam = "Fiorentina",
                league = "Italian Serie A",
                market = "Home Win (1)",
                odds = 1.72,
                matchDate = "2026-10-12",
                displayDate = "Monday, 12 Oct 2026",
                kickoffTime = "20:45",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.THREE_PLUS.storageKey,
                resultState = PredictionResult.PENDING.name
            ),
            PredictionEntity(
                id = "vip-3plus-3",
                homeTeam = "Tottenham Hotspur",
                awayTeam = "Everton",
                league = "English Premier League",
                market = "Home Win & Over 2.5",
                odds = 1.90,
                matchDate = "2026-10-13",
                displayDate = "Tuesday, 13 Oct 2026",
                kickoffTime = "19:45",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.THREE_PLUS.storageKey,
                resultState = PredictionResult.PENDING.name
            ),
            PredictionEntity(
                id = "vip-3plus-4",
                homeTeam = "Monaco",
                awayTeam = "Lille",
                league = "French Ligue 1",
                market = "Both Teams to Score (BTTS)",
                odds = 1.68,
                matchDate = "2026-10-13",
                displayDate = "Tuesday, 13 Oct 2026",
                kickoffTime = "21:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.THREE_PLUS.storageKey,
                resultState = PredictionResult.PENDING.name
            ),

            // ================= 5+ TIPS VIP (Active) =================
            PredictionEntity(
                id = "vip-5plus-1",
                homeTeam = "Atletico Madrid",
                awayTeam = "Athletic Bilbao",
                league = "Spanish La Liga",
                market = "Home Win & Under 3.5 Goals",
                odds = 2.10,
                matchDate = "2026-10-12",
                displayDate = "Monday, 12 Oct 2026",
                kickoffTime = "21:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.FIVE_PLUS.storageKey,
                resultState = PredictionResult.PENDING.name
            ),
            PredictionEntity(
                id = "vip-5plus-2",
                homeTeam = "Atalanta",
                awayTeam = "Roma",
                league = "Italian Serie A",
                market = "Over 2.5 & Both Teams to Score",
                odds = 1.95,
                matchDate = "2026-10-12",
                displayDate = "Monday, 12 Oct 2026",
                kickoffTime = "20:45",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.FIVE_PLUS.storageKey,
                resultState = PredictionResult.PENDING.name
            ),
            PredictionEntity(
                id = "vip-5plus-3",
                homeTeam = "Porto",
                awayTeam = "Benfica",
                league = "Portuguese Primeira Liga",
                market = "Over 2.5 Goals",
                odds = 1.85,
                matchDate = "2026-10-13",
                displayDate = "Tuesday, 13 Oct 2026",
                kickoffTime = "20:30",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.FIVE_PLUS.storageKey,
                resultState = PredictionResult.PENDING.name
            ),
            PredictionEntity(
                id = "vip-5plus-4",
                homeTeam = "Galatasaray",
                awayTeam = "Fenerbahce",
                league = "Turkish Super Lig",
                market = "Both Teams to Score & Over 2.5",
                odds = 1.92,
                matchDate = "2026-10-13",
                displayDate = "Tuesday, 13 Oct 2026",
                kickoffTime = "19:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.FIVE_PLUS.storageKey,
                resultState = PredictionResult.PENDING.name
            ),

            // ================= SPECIAL VIP (Active) =================
            PredictionEntity(
                id = "vip-special-1",
                homeTeam = "Manchester City",
                awayTeam = "Real Madrid",
                league = "UEFA Champions League",
                market = "Draw or Away (X2) & Over 2.5",
                odds = 2.40,
                matchDate = "2026-10-12",
                displayDate = "Monday, 12 Oct 2026",
                kickoffTime = "20:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.SPECIAL.storageKey,
                resultState = PredictionResult.PENDING.name
            ),
            PredictionEntity(
                id = "vip-special-2",
                homeTeam = "Inter Milan",
                awayTeam = "Barcelona",
                league = "UEFA Champions League",
                market = "Both Teams to Score in 2nd Half",
                odds = 2.75,
                matchDate = "2026-10-13",
                displayDate = "Tuesday, 13 Oct 2026",
                kickoffTime = "20:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.SPECIAL.storageKey,
                resultState = PredictionResult.PENDING.name
            ),

            // ================= VIP HISTORY (Completed Won & Lost) =================
            // 2+ Tips VIP History
            PredictionEntity(
                id = "hist-2plus-1",
                homeTeam = "Arsenal",
                awayTeam = "Brighton",
                league = "English Premier League",
                market = "Home Win & Over 1.5",
                odds = 1.44,
                matchDate = "2026-10-10",
                displayDate = "Saturday, 10 Oct 2026",
                kickoffTime = "15:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.TWO_PLUS.storageKey,
                resultState = PredictionResult.WON.name
            ),
            PredictionEntity(
                id = "hist-2plus-2",
                homeTeam = "Real Madrid",
                awayTeam = "Osasuna",
                league = "Spanish La Liga",
                market = "Home Win (1)",
                odds = 1.42,
                matchDate = "2026-10-10",
                displayDate = "Saturday, 10 Oct 2026",
                kickoffTime = "18:30",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.TWO_PLUS.storageKey,
                resultState = PredictionResult.WON.name
            ),
            PredictionEntity(
                id = "hist-2plus-3",
                homeTeam = "Lazio",
                awayTeam = "Torino",
                league = "Italian Serie A",
                market = "Home Win (1)",
                odds = 1.65,
                matchDate = "2026-10-09",
                displayDate = "Friday, 09 Oct 2026",
                kickoffTime = "19:45",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.TWO_PLUS.storageKey,
                resultState = PredictionResult.LOST.name
            ),

            // 3+ Tips VIP History
            PredictionEntity(
                id = "hist-3plus-1",
                homeTeam = "Bayern Munich",
                awayTeam = "Stuttgart",
                league = "German Bundesliga",
                market = "Home Win & Over 2.5",
                odds = 1.68,
                matchDate = "2026-10-10",
                displayDate = "Saturday, 10 Oct 2026",
                kickoffTime = "17:30",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.THREE_PLUS.storageKey,
                resultState = PredictionResult.WON.name
            ),
            PredictionEntity(
                id = "hist-3plus-2",
                homeTeam = "Paris Saint-Germain",
                awayTeam = "Strasbourg",
                league = "French Ligue 1",
                market = "Home Win & Over 2.5",
                odds = 1.78,
                matchDate = "2026-10-10",
                displayDate = "Saturday, 10 Oct 2026",
                kickoffTime = "20:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.THREE_PLUS.storageKey,
                resultState = PredictionResult.WON.name
            ),
            PredictionEntity(
                id = "hist-3plus-3",
                homeTeam = "Valencia",
                awayTeam = "Getafe",
                league = "Spanish La Liga",
                market = "Under 1.5 Goals",
                odds = 1.88,
                matchDate = "2026-10-09",
                displayDate = "Friday, 09 Oct 2026",
                kickoffTime = "20:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.THREE_PLUS.storageKey,
                resultState = PredictionResult.LOST.name
            ),

            // 5+ Tips VIP History
            PredictionEntity(
                id = "hist-5plus-1",
                homeTeam = "Chelsea",
                awayTeam = "Newcastle United",
                league = "English Premier League",
                market = "Both Teams to Score & Over 2.5",
                odds = 1.95,
                matchDate = "2026-10-10",
                displayDate = "Saturday, 10 Oct 2026",
                kickoffTime = "17:30",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.FIVE_PLUS.storageKey,
                resultState = PredictionResult.WON.name
            ),
            PredictionEntity(
                id = "hist-5plus-2",
                homeTeam = "Bologna",
                awayTeam = "Milan",
                league = "Italian Serie A",
                market = "Away Win & Over 1.5",
                odds = 2.20,
                matchDate = "2026-10-10",
                displayDate = "Saturday, 10 Oct 2026",
                kickoffTime = "19:45",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.FIVE_PLUS.storageKey,
                resultState = PredictionResult.WON.name
            ),
            PredictionEntity(
                id = "hist-5plus-3",
                homeTeam = "Rennes",
                awayTeam = "Lyon",
                league = "French Ligue 1",
                market = "Over 3.5 Goals",
                odds = 2.45,
                matchDate = "2026-10-09",
                displayDate = "Friday, 09 Oct 2026",
                kickoffTime = "20:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.FIVE_PLUS.storageKey,
                resultState = PredictionResult.LOST.name
            ),

            // Special VIP History
            PredictionEntity(
                id = "hist-special-1",
                homeTeam = "Barcelona",
                awayTeam = "Bayern Munich",
                league = "UEFA Champions League",
                market = "Both Teams to Score & Over 3.5",
                odds = 2.35,
                matchDate = "2026-10-08",
                displayDate = "Thursday, 08 Oct 2026",
                kickoffTime = "20:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.SPECIAL.storageKey,
                resultState = PredictionResult.WON.name
            ),
            PredictionEntity(
                id = "hist-special-2",
                homeTeam = "Liverpool",
                awayTeam = "Bayer Leverkusen",
                league = "UEFA Champions League",
                market = "Home Win & Both Teams to Score",
                odds = 2.65,
                matchDate = "2026-10-08",
                displayDate = "Thursday, 08 Oct 2026",
                kickoffTime = "20:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.SPECIAL.storageKey,
                resultState = PredictionResult.WON.name
            ),
            PredictionEntity(
                id = "hist-special-3",
                homeTeam = "Juventus",
                awayTeam = "Sporting CP",
                league = "UEFA Champions League",
                market = "Home Win to Nil",
                odds = 2.80,
                matchDate = "2026-10-07",
                displayDate = "Wednesday, 07 Oct 2026",
                kickoffTime = "20:00",
                section = MainSection.VIP.name,
                vipCategory = VipCategory.SPECIAL.storageKey,
                resultState = PredictionResult.LOST.name
            )
        )
    }
}
