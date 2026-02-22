plugins {
    id("java")
    id("com.gradleup.shadow") version "9.3.1"
    id("com.diffplug.spotless") version "8.2.1"
}

group = "dev.awe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/releases/")
    maven("https://jitpack.io")
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://mvn.lumine.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    compileOnly("de.exlll:configlib-paper:4.8.1")
    compileOnly("me.clip:placeholderapi:2.12.2")
    compileOnly("com.github.NuVotifier:NuVotifier:2.7.2")
    compileOnly("de.tr7zw:item-nbt-api:2.15.5")
    compileOnly("com.ticxo.modelengine:ModelEngine:R4.1.0")
    compileOnly("io.github.toxicity188:bettermodel-bukkit-api:2.0.1")

    implementation("com.zaxxer:HikariCP:7.0.2")
    implementation("com.mysql:mysql-connector-j:9.6.0")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.5.7")
}

tasks.shadowJar {
    exclude("plugin.yml")
    minimize()
    archiveClassifier.set("")
}

tasks.build {
    dependsOn("shadowJar")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.processResources {
    val props = mapOf("version" to project.version.toString())
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("paper-plugin.yml") {
        expand(props)
    }
}

spotless {
    format("misc") {
        target("*.gradle", "*.gradle.kts", "*.gitattributes", "*.gitignore")
        trimTrailingWhitespace()
        leadingTabsToSpaces()
        endWithNewline()
    }

    java {
        target("src/*/java/**/*.java")
        palantirJavaFormat("2.83.0")
        formatAnnotations()
        importOrder()
        removeUnusedImports()
        cleanthat()
    }
}
