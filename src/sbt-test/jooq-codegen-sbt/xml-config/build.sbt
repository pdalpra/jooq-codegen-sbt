val test = project.in(file("."))
  .enablePlugins(JOOQPlugin)
  .settings(libraryDependencies += "org.jooq" % "jooq-codegen" % "3.4.2")
  .settings(libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1102-jdbc41" % "compile")
  .settings(jdbcXml := Some( 
    <jdbc>
      <driver>org.postgresql.Driver</driver>
      <url>jdbc:postgresql:reactivefeeds</url>
      <user>reactivefeeds</user>
      <password>reactivefeeds</password>
    </jdbc>))
  .settings(generatorXml := Some(
    <generator>
      <name>org.jooq.util.DefaultGenerator</name>
      <database>
        <name>org.jooq.util.postgres.PostgresDatabase</name>
        <includes>.*</includes>
        <excludes></excludes>
        <inputSchema>public</inputSchema>
      </database>
      <target>
        <packageName>org.jooq.util.maven.example</packageName>
        <directory>target/jooq</directory>
      </target>
    </generator>))
