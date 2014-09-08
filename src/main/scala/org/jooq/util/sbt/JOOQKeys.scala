package org.jooq.util.sbt

import sbt._

import org.jooq.util.sbt.model.{ Generator, Jdbc }

import scala.xml.Elem

object JOOQKeys {

  val jdbcXml = settingKey[Option[Elem]]("JDBC configuration, as an XML literal")
  val generatorXml = settingKey[Option[Elem]]("Generator configuration, as an XML literal")

  val jdbc = settingKey[Option[Jdbc]]("JDBC configuration, as a case class")
  val generator = settingKey[Option[Generator]]("Generator configuration, as a case class")

  val jooqOutputDirectory = settingKey[File]("Output directory for the generated metamodel")
  val showGenerationLog = settingKey[Boolean]("Controls if jOOQ generation log are printed or not")
  val configFile = settingKey[File]("jOOQ config file passed to the generator")
  val generate = taskKey[Seq[File]]("Generate jOOQ metamodel")
}
