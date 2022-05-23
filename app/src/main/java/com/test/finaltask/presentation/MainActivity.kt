package com.test.finaltask.presentation

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.test.finaltask.R
import com.test.finaltask.databinding.ActivityMainBinding
import com.test.utils.navigation.NavCommand
import com.test.utils.navigation.NavCommands
import com.test.utils.navigation.NavigationProvider

class MainActivity : AppCompatActivity(), NavigationProvider {
    private lateinit var binding: ActivityMainBinding

    private val navController: NavController
        get() = findNavController(R.id.containerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun launch(navCommand: NavCommand) {
        when (val target = navCommand.target) {
            is NavCommands.DeepLink -> openDeepLink(
                url = target.url,
                isSingleTop = target.isSingleTop
            )
        }
    }

    private fun openDeepLink(url: Uri, isSingleTop: Boolean) {
        val navOptions = NavOptions.Builder()
            .setEnterAnim(com.test.utils.R.anim.slide_in_up_enter)
            .setExitAnim(com.test.utils.R.anim.slide_out_up_exit)
            .setPopEnterAnim(com.test.utils.R.anim.slide_in_up_exit)
            .setPopExitAnim(com.test.utils.R.anim.slide_out_up_exit)
            .setLaunchSingleTop(isSingleTop)
            .setPopUpTo(
                if (isSingleTop) R.id.nav_graph_application else -1,
                inclusive = isSingleTop
            )
            .build()

        navController.navigate(url, navOptions)
    }
}