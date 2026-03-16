## Apparel Editor Starter Kit

<div align="center">
  <img src="screenshot.png" width="300">
</div>

The starter kit is built on top of the Creative Editor SDK. 
Custom, mobile apparel UI for creating a print-ready design. The editable page is overlaid on a t-shirt mockup to give users an idea of where to position elements. Toggling from edit to preview mode allows reviewing the design in the context of the entire t-shirt. A floating action button at the bottom of the editor features only the most essential editing options in order of relevance allowing users to overlay text, add images, shapes, stickers and upload new image assets.

### Repository Structure

- Repository is a fully functional Android project with a single `:app` module.
- Starting point of the starter kit implementation is at `ApparelConfigurationBuilder.kt`.
- Demo app launches `EditorActivity.kt` that displays the `Editor` composable using `ApparelConfigurationBuilder`.

### Building The Repository

1. Clone the repository.
2. [Create and launch](https://developer.android.com/studio/run/managing-avds) a new android emulator or use an existing one. 
3. Open the local repository via `Android Studio` and click the `Run` button or go to the local repository via terminal and call `./gradlew installDebug`.

### Useful links

- [Starter Kit Documentation](https://img.ly/docs/cesdk/android/prebuilt-solutions/t-shirt-designer-02b48f/)
- [CESDK Android Documentation](https://img.ly/docs/cesdk/android)
- [CESDK Android Source Code](https://github.com/imgly/cesdk-android)
- [CESDK Android Examples and Play Store App Code](https://github.com/imgly/cesdk-android-examples)
