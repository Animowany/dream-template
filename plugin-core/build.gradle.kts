import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.github.jengelman.gradle.plugins.shadow.transformers.PropertiesFileTransformer

repositories {
    maven("https://repo.codemc.io/repository/nms")
    maven("https://repo.codemc.io/repository/maven-public")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    // -- spigot api -- (base)
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")

    // -- dream-platform --
    implementation("cc.dreamcode.platform:bukkit:1.13.2")
    implementation("cc.dreamcode.platform:bukkit-config:1.13.2")
    implementation("cc.dreamcode.platform:dream-command:1.13.2")

    // -- dream-utilties --
    implementation("cc.dreamcode:utilities-adventure:1.5.5")

    // -- dream-notice --
    implementation("cc.dreamcode.notice:bukkit:1.7.0")
    implementation("cc.dreamcode.notice:bukkit-serializer:1.7.0")

    // -- dream-command --
    implementation("cc.dreamcode.command:bukkit:2.2.1")

    // -- dream-menu --
    implementation("cc.dreamcode.menu:bukkit:1.4.0")
    implementation("cc.dreamcode.menu:bukkit-serializer:1.4.0")

    // -- tasker (easy sync/async scheduler) --
    implementation("eu.okaeri:okaeri-tasker-bukkit:3.0.2-beta.5")
}

tasks.withType<ShadowJar> {

    archiveFileName.set("Dream-Template-${project.version}.jar")
    mergeServiceFiles()

    relocate("com.cryptomorin", "cc.dreamcode.template.libs.com.cryptomorin")
    relocate("eu.okaeri", "cc.dreamcode.template.libs.eu.okaeri")
    relocate("net.kyori", "cc.dreamcode.template.libs.net.kyori")

    relocate("cc.dreamcode.platform", "cc.dreamcode.template.libs.cc.dreamcode.platform")
    relocate("cc.dreamcode.utilities", "cc.dreamcode.template.libs.cc.dreamcode.utilities")
    relocate("cc.dreamcode.menu", "cc.dreamcode.template.libs.cc.dreamcode.menu")
    relocate("cc.dreamcode.command", "cc.dreamcode.template.libs.cc.dreamcode.command")
    relocate("cc.dreamcode.notice", "cc.dreamcode.template.libs.cc.dreamcode.notice")

    minimize()
}