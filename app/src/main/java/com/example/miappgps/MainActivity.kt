package com.example.miappgps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.miappgps.ui.AppNavigation
import com.example.miappgps.ui.theme.MiAppGPSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiAppGPSTheme {
                AppNavigation()
            }
        }
    }
}