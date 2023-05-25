plugins { 
    id("java")
    id("groovy")
}

group = "org.mars.rover.kata"
version = "1.0-SNAPSHOT"

repositories { 
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    testCompileOnly("org.projectlombok:lombok:1.18.26")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.26")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.assertj:assertj-core:3.24.2") // Fluent assertions
    testImplementation("org.spockframework:spock-core:2.4-M1-groovy-4.0")

}

tasks.test { 
    useJUnitPlatform()
}
