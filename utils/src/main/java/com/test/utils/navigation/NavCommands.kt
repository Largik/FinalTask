package com.test.utils.navigation

import android.net.Uri

sealed class NavCommands {
    data class DeepLink(
        val url: Uri,
        val isSingleTop: Boolean = false
    ) : NavCommands()
}
