plugins {
  kotlin("jvm") version "2.2.20"
}

group = "com.veterinaria"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(kotlin("test"))
  implementation(kotlin("stdlib"))
  implementation(kotlin("reflect"))
}

tasks.test {
  useJUnitPlatform()
}
kotlin {
  jvmToolchain(21)
}