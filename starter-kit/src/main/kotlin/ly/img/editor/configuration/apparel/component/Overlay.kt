package ly.img.editor.configuration.apparel.component

import androidx.compose.runtime.Composable
import ly.img.editor.configuration.apparel.ApparelConfigurationBuilder
import ly.img.editor.core.component.EditorComponent
import ly.img.editor.core.component.remember

@Composable
fun ApparelConfigurationBuilder.rememberOverlay() = EditorComponent.remember {
    decoration = { Overlay() }
}
