package com.itis.lemonai.android.navigation

import android.icu.text.CaseMap.Title
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.FeatherIcons
import compose.icons.feathericons.Archive
import compose.icons.feathericons.Camera
import compose.icons.feathericons.User

sealed class BottomItem(val title: String, val imageVector: ImageVector, val route: String) {
    object ScreenCamera: BottomItem("Камера", FeatherIcons.Camera, "MainCameraScreen")
    object ScreenHistory: BottomItem("История", FeatherIcons.Archive, "HistoryScreen")
    object ScreenProfile: BottomItem("Профиль", FeatherIcons.User, "ProfileScreen")
}