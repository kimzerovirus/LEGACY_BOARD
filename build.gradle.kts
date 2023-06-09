import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    war
    id("org.springframework.boot") version "3.0.4"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
    kotlin("kapt") version "1.7.21" //Querydsl
}

group = "me.kzv"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.batch:spring-batch-test")
    testImplementation("org.springframework.security:spring-security-test")

    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")

    // jsp
    // https://mvnrepository.com/artifact/javax.servlet.jsp.jstl/jstl-api
//    implementation("javax.servlet.jsp.jstl:jstl-api:1.2") // <- taglib 가 없는듯?
//    implementation("javax.servlet:jstl:1.2") // 스프링 버전이 높아서 jakarta 머시기 에러남

    implementation("org.glassfish.web:jakarta.servlet.jsp.jstl:2.0.0") // tomcat 9.0++ 버전부터는 요거 써야되는듯 - https://stackoverflow.com/questions/64597921/jakarta-servlet-servletexception-java-lang-noclassdeffounderror-javax-servlet
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper") // 스프링 부트 내장 톰캣 사용
    // https://stackoverflow.com/questions/74913190/spring-boot-apps-jar-not-working-issue-with-tomcat-embed-jasper
    // 스프링 높은 버전에서 embedded tomcat jar 실행시 이슈 있음
    implementation("org.springframework.security:spring-security-taglibs") // jsp spring security library

    // aws
    implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")

    // Querydsl 추가
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:${dependencyManagement.importedProperties["querydsl.version"]}:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

/*
  -plain.jar 는 jar 와 다르게 dependencies 를 제외한 classes 와 resources 만을 가지고 있음
  따라서 java -jar 로 실행할 수 없음
 */
tasks.named<Jar>("jar") { // plain-jar 생성 안하기
    enabled = false
}
tasks.named<War>("war") { // plain-war 생성 안하기
    enabled = false
}

// jsp jar 파일로 실행시 view 를 지원하지 않음 -> war 파일로 실행시키면 됨