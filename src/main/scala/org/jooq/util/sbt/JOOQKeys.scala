package org.jooq.util.sbt

import sbt._

import org.jooq.util.jaxb.Configuration
import org.jooq.util.sbt.model.{ Generator, Jdbc }

import scala.xml.Elem

object JOOQKeys {

  val jdbcXml = settingKey[Option[Elem]]("JDBC configuration, as an XML literal")
  val generatorXml = settingKey[Option[Elem]]("Generator configuration, as an XML literal")

  val jdbc = settingKey[Option[Jdbc]]("JDBC configuration, as a case class")
  val generator = settingKey[Option[Generator]]("Generator configuration, as a case class")

  val configuration = settingKey[Configuration]("JOOQ's code generator configuration")

  val generate = taskKey[Unit]("Generate JOOQ metamodel")
  val regenOnCompile = settingKey[Boolean]("Regenerate metamodel when compiling")

}
