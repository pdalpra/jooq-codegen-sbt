import org.jooq.util.sbt.model._

val test = project.in(file("."))
  .enablePlugins(JOOQPlugin)
  .settings(libraryDependencies += "org.jooq" % "jooq-codegen" % "3.4.2")
  .settings(libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1102-jdbc41" % "runtime")
  .settings(jdbc := Some(
    Jdbc(
      driver = Some("org.postgresql.Driver"),
      url = Some("jdbc:postgresql:reactivefeeds"),
      user = Some("reactivefeeds"),
      password = Some("reactivefeeds"))
  ))
  .settings(generator := Some(
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
      )))
  ))
