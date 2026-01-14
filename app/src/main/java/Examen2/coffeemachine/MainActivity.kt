package net.iessochoa.sergiocontreras.coffeemachine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.iessochoa.sergiocontreras.coffeemachine.ui.theme.CoffeeMachineTheme

/* Aquí ni mires, esto es solo el punto de entrada
 * de la aplicación. Se asegura que se pasa el tema y listo.
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeeMachineTheme {
                App()
            }
        }
    }
}


