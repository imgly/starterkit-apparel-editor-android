@file:Suppress("UnusedReceiverParameter")

package ly.img.editor.configuration.apparel.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import ly.img.editor.configuration.apparel.ApparelConfigurationBuilder
import ly.img.editor.core.component.DefaultDecoration
import ly.img.editor.core.component.Dock
import ly.img.editor.core.component.remember
import ly.img.editor.core.component.rememberAssetLibrary
import ly.img.editor.core.state.EditorViewMode

@Composable
fun ApparelConfigurationBuilder.rememberDock() = Dock.remember {
    visible = {
        val state by editorContext.state.collectAsState()
        state.viewMode !is EditorViewMode.Preview
    }
    decoration = {
        Dock.DefaultDecoration(
            background = Color.Transparent,
        ) { it() }
    }
    listBuilder = {
        Dock.ListBuilder.remember {
            add { Dock.Button.rememberAssetLibrary() }
        }
    }
}
