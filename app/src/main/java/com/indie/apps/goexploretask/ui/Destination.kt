package com.indie.apps.goexploretask.ui

sealed class Destination(val route: String) {
    data object Home : Destination("home")
    data object SecondScreen : Destination("second?sound={sound}&place={place}")
}
