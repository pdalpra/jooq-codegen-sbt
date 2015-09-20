import org.jooq.util.sbt.model._

enablePlugins(JOOQPlugin)

jooqVersion := "3.6.3"

libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1102-jdbc41" % "runtime"

jdbc := Some(
    Jdbc(
      driver = Some("org.postgresql.Driver"),
      url = Some("jdbc:postgresql://localhost:6000/test"),
      user = Some("test"),
      password = Some("test")
    )
  )

generator := Some(
    Generator(
      name = Some("org.jooq.util.DefaultGenerator"),
      database = Some(Database(
        name = Some("org.jooq.util.postgres.PostgresDatabase"),
        includes = Some(".*"),
        inputSchema = Some("public")
      )),
      target = Some(Target(
        packageName = Some("org.jooq.util.maven.example"),
        directory = Some("target/jooq")
      ))
    )
)
