package com.softylines.workshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.softylines.workshop.feature_pictures.UI.PictureListScreen
import com.softylines.workshop.ui.theme.WorkshopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkshopTheme {
                PictureListScreen()
            }
        }
    }
}