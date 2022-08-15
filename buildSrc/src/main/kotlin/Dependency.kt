object Dependency {
    // r2dbc
    const val R2DBC = "org.springframework.boot:spring-boot-starter-data-r2dbc"

    //validation
    const val VALIDATION = "org.springframework.boot:spring-boot-starter-validation"

    // web
    const val WEB = "org.springframework.boot:spring-boot-starter-web"

    // jackson
    const val JACKSON = "com.fasterxml.jackson.module:jackson-module-kotlin"

    // reflect
    const val REFLECT = "org.jetbrains.kotlin:kotlin-reflect"

    // jdk8
    const val STDLIB_JDK8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"

    // test
    const val TEST = "org.springframework.boot:spring-boot-starter-test"

    // reactor
    object Reactor {
        const val KOTLIN_EXTENSIONS = "io.projectreactor.kotlin:reactor-kotlin-extensions"
        const val TEST = "io.projectreactor:reactor-test"
        const val KOTLINX_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-reactor"
    }
}