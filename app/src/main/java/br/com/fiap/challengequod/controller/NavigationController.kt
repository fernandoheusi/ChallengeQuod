package br.com.fiap.challengequod.controller

import androidx.navigation.NavController

class NavigationController(private val navController: NavController) {
    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    fun goBack() {
        navController.popBackStack()
    }
}
