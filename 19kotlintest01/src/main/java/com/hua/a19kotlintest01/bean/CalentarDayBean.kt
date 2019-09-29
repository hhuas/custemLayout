package com.hua.a19kotlintest01.bean

data class CalentarDayBean(
        val reason: String,
        val result: CalentarDayResult,
        val error_code: Int
)

data class CalentarDayResult(
        val data: CalentarDayData
)

data class CalentarDayData(
        val date: String,
        val weekday: String,
        val animalsYear: String,
        val suit: String,
        val avoid: String,
        val yearMonth: String,
        val holiday: String,
        val lunar: String,
        val lunarYear: String,
        val desc: String
)
