package com.example.apipractice

sealed class Routes(val route: String) {
    object MainScreen : Routes("main_screen")
    object DetailScreen : Routes("detail_screen/{characterId}") {
        fun createRoute(characterId: String): String {
            return "detail_screen/$characterId"
        }

    }
}