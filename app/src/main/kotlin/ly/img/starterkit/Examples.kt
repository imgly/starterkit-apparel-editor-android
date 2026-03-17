package ly.img.starterkit

import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import ly.img.editor.Editor
import ly.img.editor.EditorUiMode
import ly.img.editor.configuration.apparel.ApparelConfigurationBuilder
import ly.img.editor.configuration.apparel.callback.onCreate
import ly.img.editor.core.configuration.EditorConfiguration
import ly.img.editor.core.configuration.remember
import ly.img.editor.core.engine.EngineRenderTarget

@Composable
private fun ExampleInlineModification(onClose: (error: Throwable?) -> Unit) {
    // highlight-starter-kit-inline-modification
    Editor(
        license = null, // pass null or empty for evaluation mode with watermark
        configuration = {
            EditorConfiguration.remember(::ApparelConfigurationBuilder) {
                onCreate = {
                    onCreate(
                        createScene = {
                            getOrLoadScene(
                                sceneUri = "https://example.com/example.scene".toUri(),
                            )
                        },
                    )
                }
            }
        },
        onClose = onClose,
    )
    // highlight-starter-kit-inline-modification
}

@Composable
private fun ExampleBaseUri(onClose: (error: Throwable?) -> Unit) {
    // highlight-starter-kit-base-uri
    Editor(
        license = null, // pass null or empty for evaluation mode with watermark
        baseUri = "file:///android_asset".toUri(), // this points to android assets
        configuration = {
            EditorConfiguration.remember(::ApparelConfigurationBuilder)
        },
        onClose = onClose,
    )
    // highlight-starter-kit-base-uri
}

@Composable
private fun ExampleUIMode(onClose: (error: Throwable?) -> Unit) {
    // highlight-starter-kit-ui-mode
    Editor(
        license = null, // pass null or empty for evaluation mode with watermark
        uiMode = EditorUiMode.SYSTEM, // EditorUiMode.SYSTEM, EditorUiMode.LIGHT, EditorUiMode.DARK
        configuration = {
            EditorConfiguration.remember(::ApparelConfigurationBuilder)
        },
        onClose = onClose,
    )
    // highlight-starter-kit-ui-mode
}

@Composable
private fun ExampleEngineRenderTarget(onClose: (error: Throwable?) -> Unit) {
    // highlight-starter-kit-engine-render-target
    Editor(
        license = null, // pass null or empty for evaluation mode with watermark
        engineRenderTarget = EngineRenderTarget.SURFACE_VIEW, // EngineRenderTarget.SURFACE_VIEW, EngineRenderTarget.TEXTURE_VIEW
        configuration = {
            EditorConfiguration.remember(::ApparelConfigurationBuilder)
        },
        onClose = onClose,
    )
    // highlight-starter-kit-engine-render-target
}
