plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
/*    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")*/
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
}
tasks.test {
    useJUnitPlatform()
    testLogging.showStandardStreams=true; // to make console display err & out messages
}