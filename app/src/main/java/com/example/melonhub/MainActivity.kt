package com.example.melonhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.melonhub.navegacion.AppNavHost
import com.example.melonhub.navegacion.MyAppBottomBar
import com.example.melonhub.navegacion.MyAppTopBar
import com.example.melonhub.ui.theme.MelonhubTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController(

            )
            MelonhubTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { MyAppTopBar(navController = navController) },
                    bottomBar = { MyAppBottomBar(navController = navController) }
                ) { innerPadding ->

                    AppNavHost(
                        navController = navController,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}