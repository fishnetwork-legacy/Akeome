plugins {
    id("java")
    id("eclipse")
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "Akeome"
version = "1.0.0"

repositories{
    mavenCentral()
    flatDir {
        dirs("lib")
    }
}

dependencies{
    annotationProcessor("org.projectlombok", "lombok", "1.18.22")
    compileOnly("org.powernukkit", "powernukkit", "1.5.1.0-PN")
    compileOnly("fishnetwork.userapi", "UserAPI", "1.0.0")
    compileOnly("org.projectlombok", "lombok", "1.18.22")
}

tasks{
    compileJava{
        options.encoding = "UTF-8"
    }
}