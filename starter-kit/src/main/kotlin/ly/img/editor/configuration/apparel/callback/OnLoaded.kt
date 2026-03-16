package ly.img.editor.configuration.apparel.callback

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ly.img.editor.configuration.apparel.ApparelConfigurationBuilder
import ly.img.editor.core.state.EditorViewMode
import ly.img.engine.SizeMode

suspend fun ApparelConfigurationBuilder.onLoaded() {
    coroutineScope {
        launch { observeEditorViewMode() }
        launch { observeEditorEditMode() }
        launch { observePageSizeChanges() }
        launch { observeIsTouchActive() }
    }
}

suspend fun ApparelConfigurationBuilder.observeEditorViewMode() {
    editorContext.state
        .distinctUntilChangedBy { it.viewMode to it.insets }
        .filter { it.viewMode is EditorViewMode.Preview }
        .collect { state ->
            val engine = editorContext.engine
            val scene = requireNotNull(engine.scene.get())
            val backdropImage =
                engine.block.getChildren(scene).first { engine.block.getKind(it) == "image" }
            engine.scene.immediateZoomToBlock(
                block = backdropImage,
                paddingLeft = state.insets.left.value,
                paddingTop = state.insets.top.value,
                paddingRight = state.insets.right.value,
                paddingBottom = 0F,
                forceUpdate = true,
            )
        }
}

suspend fun ApparelConfigurationBuilder.observePageSizeChanges() {
    val engine = editorContext.engine
    val page = requireNotNull(engine.scene.getCurrentPage())
    val outline = engine.block.findByName(ApparelConfigurationBuilder.Companion.OUTLINE_BLOCK_NAME).first()
    engine.event.subscribe(listOf(page))
        .map { engine.block.getWidth(block = page) to engine.block.getHeight(block = page) }
        .distinctUntilChanged()
        .collect { (width, height) ->
            engine.block.setHeightMode(block = outline, mode = SizeMode.ABSOLUTE)
            engine.block.setHeight(block = outline, value = height)
            engine.block.setWidthMode(block = outline, mode = SizeMode.ABSOLUTE)
            engine.block.setWidth(block = outline, value = width)
            engine.block.setPositionX(
                block = outline,
                value = engine.block.getPositionX(block = page),
            )
            engine.block.setPositionY(
                block = outline,
                value = engine.block.getPositionY(block = page),
            )
        }
}

suspend fun ApparelConfigurationBuilder.observeIsTouchActive() {
    val engine = editorContext.engine
    val outline = engine.block.findByName(ApparelConfigurationBuilder.Companion.OUTLINE_BLOCK_NAME).first()
    editorContext.state
        .distinctUntilChangedBy { it.isTouchActive }
        .collect {
            engine.block.setVisible(block = outline, visible = it.isTouchActive)
            engine.block.setOpacity(block = outline, value = if (it.isTouchActive) 1F else 0F)
        }
}
