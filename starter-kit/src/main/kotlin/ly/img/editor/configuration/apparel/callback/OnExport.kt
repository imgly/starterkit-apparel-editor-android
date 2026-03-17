package ly.img.editor.configuration.apparel.callback

import kotlinx.coroutines.CancellationException
import ly.img.editor.configuration.apparel.ApparelConfigurationBuilder
import ly.img.engine.MimeType
import java.nio.ByteBuffer

suspend fun ApparelConfigurationBuilder.onExport(
    preExport: suspend ApparelConfigurationBuilder.() -> Unit = {
        onPreExport()
    },
    exportByteBuffer: suspend ApparelConfigurationBuilder.() -> ByteBuffer = {
        onExportByteBuffer()
    },
    postExport: suspend ApparelConfigurationBuilder.(ByteBuffer) -> Unit = {
        onPostExport(it)
    },
    error: suspend ApparelConfigurationBuilder.(Exception) -> Unit = {
        onExportError(it)
    },
    finally: suspend ApparelConfigurationBuilder.() -> Unit = {
        onExportFinally()
    },
) {
    try {
        preExport()
        val result = exportByteBuffer()
        postExport(result)
    } catch (exception: Exception) {
        error(exception)
    } finally {
        finally()
    }
}

fun ApparelConfigurationBuilder.onPreExport() {
    showLoading = true
}

suspend fun ApparelConfigurationBuilder.onExportByteBuffer(): ByteBuffer = export(
    block = requireNotNull(editorContext.engine.scene.get()),
    mimeType = MimeType.PDF,
)

suspend fun ApparelConfigurationBuilder.onPostExport(byteBuffer: ByteBuffer) {
    val file = writeToFile(byteBuffer = byteBuffer, mimeType = MimeType.PDF)
    shareFile(file = file, mimeType = MimeType.PDF)
}

fun ApparelConfigurationBuilder.onExportError(error: Exception) {
    if (error is CancellationException) {
        throw error
    }
    this.error = error
}

fun ApparelConfigurationBuilder.onExportFinally() {
    showLoading = false
}
