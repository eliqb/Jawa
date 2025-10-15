plugins {
    id("java-library")
    id("application")
}

repositories {
    mavenCentral()
}

dependencies {
    api(project(":compiler:common"))
}

application {
    mainClass = "org.jawalang.jawac.cli.Main"
}

sourceSets {
    main {
        java.srcDirs("src")
    }
}