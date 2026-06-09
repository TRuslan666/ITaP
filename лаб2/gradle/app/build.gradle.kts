plugins {
    id("java")
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("org.example.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":string-utils"))
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    manifest {
        attributes(Pair("Main-Class", "org.example.Main"))
    }
}

abstract class PrintInfoTask : DefaultTask() {
    @org.gradle.api.tasks.TaskAction
    fun print() {
        println("======================================")
        println("Это моя первая пользовательская задача!")
        println("Проект: ${project.name}")
        println("Версия Gradle: ${project.gradle.gradleVersion}")
        println("======================================")
    }
}

tasks.register<PrintInfoTask>("printInfo") {
    group = "Custom"
    description = "Выводит информацию о проекте"
}

abstract class GenerateBuildPassportTask : DefaultTask() {

    @get:org.gradle.api.tasks.Input
    abstract val gitCommitHash: Property<String>

    @org.gradle.api.tasks.TaskAction
    fun generate() {
        val resourcesDir = File(project.projectDir, "src/main/resources")
        resourcesDir.mkdirs()
        val outputFile = File(resourcesDir, "build-passport.properties")

        // Читаем предыдущий номер сборки и инкрементируем
        var buildNumber = 1
        if (outputFile.exists()) {
            outputFile.readLines()
                .find { it.startsWith("build.number=") }
                ?.removePrefix("build.number=")
                ?.trim()
                ?.toIntOrNull()
                ?.let { buildNumber = it + 1 }
        }

        val user = System.getenv("USERNAME") ?: System.getenv("USER") ?: "unknown"
        val os = System.getProperty("os.name")
        val javaVersion = System.getProperty("java.version")
        val now = java.time.LocalDateTime.now()
            .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        outputFile.writeText("""
            build.number=$buildNumber
            build.user=$user
            build.os=$os
            build.java=$javaVersion
            build.time=$now
            build.git.hash=${gitCommitHash.get()}
            build.message=Assembled with Gradle. Have a nice day!
        """.trimIndent())

        println("Build passport generated: $outputFile (build #$buildNumber)")
    }
}

tasks.register<GenerateBuildPassportTask>("generateBuildInfo") {
    group = "Custom"
    description = "Генерирует build-passport.properties"

    // Получаем хеш последнего git-коммита
    val hash = try {
        val proc = ProcessBuilder("git", "rev-parse", "--short", "HEAD")
            .directory(project.projectDir)
            .start()
        proc.inputStream.bufferedReader().readLine()?.trim() ?: "unknown"
    } catch (e: Exception) {
        "unknown"
    }
    gitCommitHash.set(hash)
}

tasks.named("processResources") {
    dependsOn(tasks.named("generateBuildInfo"))
}