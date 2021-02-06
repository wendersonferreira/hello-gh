import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    kotlin("jvm") version "1.4.30"
}

val group = "br.com.trustsystems"
val version = "0.1.0-SNAPSHOT"
val tag = generateTag(version)

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit:junit:4.13.1")
}

//java {
//    toolchain {
//        languageVersion.set(JavaLanguageVersion.of(8))
//        vendor.set(JvmVendorSpec.BELLSOFT)
//    }
//}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    wrapper {
        gradleVersion = "6.8.2"
        distributionType = Wrapper.DistributionType.BIN
    }
}

fun generateTag(version: String): String {
    return when (version.substringAfterLast("-")) {
        "SNAPSHOT" ->
            version.replace("-SNAPSHOT", "")
                .plus("_").plus(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))

        else -> version
    }
}
