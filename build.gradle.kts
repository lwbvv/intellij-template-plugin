
// 플러그인을 만들 때 필요한 플러그인 디펜던시들을 정의한다
plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.6.20"
    id("org.jetbrains.intellij") version "1.7.0"
}

group = "com.richone"
version = "0.0.1"

//apply {
//    plugin("org.jetbrains.intellij")
//}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fleshgrinder.kotlin:case-format:0.2.0")
//    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2022.2") // 플러그인을 빌드하는 데 사용할 인텔리제이 버전
    type.set("IC") // https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html#configuration-intellij-extension

    plugins.set(listOf("java", "Kotlin"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    patchPluginXml {
        sinceBuild.set("212") // 플러그인을 사용할 수 있는 IDE의 최소 버젼
        untilBuild.set("223.*") // 플러그인을 사용할 수 있는 IDE의 최대 버전
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
