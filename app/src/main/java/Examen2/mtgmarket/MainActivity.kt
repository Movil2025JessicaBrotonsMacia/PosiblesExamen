package net.iessochoa.sergiocontreras.mtgmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.iessochoa.sergiocontreras.mtgmarket.ui.theme.MtgMarketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MtgMarketTheme() {
                App()
            }
        }
    }
}
