package ly.img.editor.configuration.apparel

import androidx.compose.runtime.Stable
import ly.img.editor.BasicConfigurationBuilder
import ly.img.editor.configuration.apparel.callback.onCreate
import ly.img.editor.configuration.apparel.callback.onExport
import ly.img.editor.configuration.apparel.callback.onLoaded
import ly.img.editor.configuration.apparel.component.rememberCanvasMenu
import ly.img.editor.configuration.apparel.component.rememberDock
import ly.img.editor.configuration.apparel.component.rememberInspectorBar
import ly.img.editor.configuration.apparel.component.rememberNavigationBar
import ly.img.editor.configuration.apparel.component.rememberOverlay
import ly.img.editor.core.EditorScope
import ly.img.editor.core.ScopedProperty
import ly.img.editor.core.component.EditorComponent

@Stable
class ApparelConfigurationBuilder : BasicConfigurationBuilder() {
    override var onCreate: (suspend EditorScope.() -> Unit)? = {
        onCreate()
    }

    override var onLoaded: (suspend EditorScope.() -> Unit)? = {
        onLoaded()
    }

    override var onExport: (suspend EditorScope.() -> Unit)? = {
        onExport()
    }

    override var onClose: (suspend EditorScope.() -> Unit)? = {
        showConfirmationOrCloseEditor()
    }

    override var onError: (suspend EditorScope.(Throwable) -> Unit)? = {
        error = it
    }

    override var dock: ScopedProperty<EditorScope, EditorComponent<*>>? = {
        rememberDock()
    }

    override var navigationBar: ScopedProperty<EditorScope, EditorComponent<*>>? = {
        rememberNavigationBar()
    }

    override var inspectorBar: ScopedProperty<EditorScope, EditorComponent<*>>? = {
        rememberInspectorBar()
    }

    override var canvasMenu: ScopedProperty<EditorScope, EditorComponent<*>>? = {
        rememberCanvasMenu()
    }

    override var overlay: ScopedProperty<EditorScope, EditorComponent<*>>? = {
        rememberOverlay()
    }

    companion object {
        const val OUTLINE_BLOCK_NAME = "ly.img.editor.apparel.outline"
    }
}
