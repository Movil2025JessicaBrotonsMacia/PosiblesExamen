package net.iessochoa.sergiocontreras.doctorround

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import net.iessochoa.sergiocontreras.doctorround.ui.DoctorRoundApp
import net.iessochoa.sergiocontreras.doctorround.ui.theme.DoctorRoundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoctorRoundTheme {
                DoctorRoundApp()
            }
        }
    }
}