package ly.img.starterkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ly.img.editor.Editor
import ly.img.editor.configuration.apparel.ApparelConfigurationBuilder
import ly.img.editor.core.configuration.EditorConfiguration
import ly.img.editor.core.configuration.remember

class EditorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Editor(
                configuration = {
                    EditorConfiguration.remember(::ApparelConfigurationBuilder)
                },
            ) {
                finish()
            }
        }
    }
}
