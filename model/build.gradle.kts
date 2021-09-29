import org.jooq.meta.jaxb.Logging
import org.jooq.meta.jaxb.Property

plugins {
    id("org.liquibase.gradle") version "2.0.3"
    id("nu.studer.jooq")
}

dependencies {
    liquibaseRuntime("org.liquibase:liquibase-core:4.4.3")
    liquibaseRuntime("org.postgresql:postgresql:42.2.24")
    liquibaseRuntime("org.yaml:snakeyaml:1.29")
    liquibaseRuntime("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
    jooqGenerator("org.jooq:jooq-meta-extensions:3.15.3")
}

liquibase {
    activities.register("main") {
        this.arguments = mapOf(
            "logLevel" to "info",
            "classpath" to "$projectDir/src/main/resources",
            "changeLogFile" to "liquibase-changelog.yaml",
            "url" to "jdbc:postgresql://localhost:5432/foo",
            "username" to "foo",
            "password" to "",
        )
    }
    runList = "main"
}

jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                logging = Logging.WARN
                jdbc = null
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.extensions.ddl.DDLDatabase"
                        properties.add(
                            Property().withKey("scripts").withValue("src/main/resources/migration")
                        )
                        properties.add(
                            Property().withKey("defaultNameCase").withValue("lower")
                        )
                    }
                    generate.apply {
                        isDaos = false
                        isPojosEqualsAndHashCode = true
                    }
                    target.apply {
                        packageName = "com.example.foo.model.jooq"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}