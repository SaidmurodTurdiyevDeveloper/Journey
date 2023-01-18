/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/6/2023.
 */
object Versions {
    //deps
    const val core = "1.9.0"
    const val appCompat = "1.5.1"
    const val material = "1.7.0"
    const val constraint = "2.1.4"
    const val legacy = "1.0.0"
    const val timber = "5.0.1"

    //test
    const val jUnit = "4.13.2"

    //androidTest
    const val android_JUnit = "1.1.5"
    const val espresso = ""

    //lifeCycle
    const val lifeCycle = "2.5.1"

    //compose
    const val composeUI = "1.3.2"
    const val composeActivity = "1.6.1"
    const val composeMaterial3 = "1.1.0-alpha03"

    //navigation
    const val navigation = "2.5.3"

    //imageLoader
    const val glide = "4.14.2"
    const val picasso="2.71828"

    //coroutine
    const val coroutine = "1.6.4"

    //viewBinding
    const val viewBinding = "1.5.6"

    //hilt
    const val hilt = "2.44.2"

    //permission
    const val dexter = "6.2.3"

    //room
    const val room = "2.4.3"

    //paging
    const val paging = "3.1.1"
    const val composePaging = "1.0.0-alpha17"

    //splash
    const val circleIndicator="2.1.6"
    const val dotsIndicator="4.3"

    //carousel
    const val imageCarousel="2.1.0"

    //image
    const val compressor="3.0.1"

    //expansion
    const val expansionPanel="1.2.4"
}

//androidx.core:core-ktx:1.9.0
object Deps {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object Tests {
    const val jUnit = "junit:junit:${Versions.jUnit}"
}

object AndroidTest {
    const val jUnit = "androidx.test.ext:junit:${Versions.android_JUnit}"
    const val composeJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.composeUI}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object LifeCycle {
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifeCycle}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
}

object ComposeDeps {
    const val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val ui = "androidx.compose.ui:ui:${Versions.composeUI}"
    const val uiTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.composeUI}"
    const val material3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
}

object DebugDeps {
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeUI}"
    const val composeUiManifest = "androidx.compose.ui:ui-test-manifest:${Versions.composeUI}"
}

object Navigation {
    const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object ImageLoader {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotation = "com.github.bumptech.glide:annotations:${Versions.glide}"
    const val glideIntegration = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val picasso="com.squareup.picasso:picasso:${Versions.picasso}"
}

object Coroutine {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
}

object ViewBinding {
    const val delegate = "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewBinding}"
    const val noreflection = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBinding}"
}

object Hilt {
    const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
}

object Permission {
    const val dexter = "com.karumi:dexter:${Versions.dexter}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
    const val guavu = "androidx.room:room-guava:${Versions.room}"
}

object Paging {
    const val runtime = "androidx.paging:paging-runtime:${Versions.paging}"
    const val common = "androidx.paging:paging-common:${Versions.paging}"
    const val rxJava2 = "androidx.paging:paging-rxjava2:${Versions.paging}"
    const val rxJava3 = "androidx.paging:paging-rxjava3:${Versions.paging}"
    const val guavu = "androidx.paging:paging-guava:${Versions.paging}"
    const val compose = "androidx.paging:paging-compose:${Versions.composePaging}"
}
object SplashDeps{
    const val circleIndicator="me.relex:circleindicator:${Versions.circleIndicator}"
    const val dotsIndicator="com.tbuonomo:dotsindicator:${Versions.dotsIndicator}"
}
object Carousel{
    const val imageCarousel="org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:${Versions.imageCarousel}"
}
object Image{
    const val compressor="id.zelory:compressor:${Versions.compressor}"
}
object Expansion{
    const val expansionPanel="com.github.florent37:expansionpanel:${Versions.expansionPanel}"
}