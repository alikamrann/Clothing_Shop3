package com.alikamran.clothingshop.bottomnav

import com.alikamran.clothingshop.R


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {

    // for home
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_launcher_foreground,
        icon_focused = R.drawable.ic_launcher_foreground
    )

    // for report
    object Report: BottomBarScreen(
        route = "report",
        title = "Report",
        icon = R.drawable.ic_launcher_foreground,
        icon_focused = R.drawable.ic_launcher_foreground
    )

    // for report
    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.ic_launcher_foreground,
        icon_focused = R.drawable.ic_launcher_foreground
    )

}
