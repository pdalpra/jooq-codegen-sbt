jooq-codegen-sbt
================

## Usage

### Versionning

`jooq-codegen-sbt` aligns its versionning on JOOQ, introducing an additional number to denote the version of the plugin itself.

For example, the first version of the plugin targeting JOOQ will be `3.6.0.0`, the next one `3.6.0.1` and so on. 

### Adding the plugin

Add this to your `plugin.sbt`:

```scala
addSbtPlugin("info.pdalpra" % "jooq-codegen-sbt" % "3.6.0.0")
```

### Configuring the plugin

In your build, add to the module you want to enable this plugin in:

```scala
enablePlugins(JOOQPlugin)
```

There are a few mandatory settings to configure:

#### `jooqVersion`

Configures the exact version of the JOOQ's code generator to use:

```scala
jooqVersion := "3.6.3"
```

#### `jdbc`/`generator` or `jdbcXml`/`generatorXml`

You can use either a XML-based or class-based configuration of the code generator.
Whatever the configuration method you decide to use, you'll need to define both settings to have a complete configuration, either `jdbc`/`generator` for the class-based configuration or `jdbcXml`/`generatorXml` for the XML-based one.

Examples:

* [Class-based configuration](https://github.com/pdalpra/jooq-codegen-sbt/blob/master/src/sbt-test/jooq-codegen-sbt/caseclasses-config/build.sbt)
* [XML-based configuration](https://github.com/pdalpra/jooq-codegen-sbt/blob/master/src/sbt-test/jooq-codegen-sbt/xml-config/build.sbt)

#### Driver dependency

Finally, you'll need to include in your project dependencies the JDBC driver for your database: 

```scala
libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1102-jdbc41" % "runtime"
```


## Building the plugin

As JOOQ requires a database to generate the metamodel, a Docker image building a sample database  is available in `test-db`.

### Building & running the Docker image

From the project root:

```bash
$ docker build -t test-db test-db
$ docker run -d --name test-db -p 6000:5432 test-db
```

### Running the tests

To build the plugin locally and run the plugin tests, simply use `sbt scripted`.
