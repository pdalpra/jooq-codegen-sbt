package org.jooq.util.sbt

import scala.xml.Elem

import sbt._
import sbt.Keys._

import org.jooq.util.sbt.ConfigurationUtils._
import org.jooq.util.sbt.model.{ Generator, Jdbc }

object JOOQPlugin extends AutoPlugin {

  // ---------------------- //
  // -- AutoPlugin setup -- //
  // ---------------------- //

  val autoImport = JOOQKeys

  import autoImport._

  override def projectSettings = JOOQSettings

  // -------------- //
  // -- Settings -- //
  // -------------- //

  val JOOQSettings = Seq(
    jdbcXml := None,
    generatorXml := None,
    jdbc := None,
    generator := None,
    jooqOutputDirectory := (sourceManaged in Compile).value / "java",
    configFile := target.value / "jooq" / "jooq-config.xml",
    generate := generateMetaModel(
      zip(jdbcXml.value, generatorXml.value),
      zip(jdbc.value, generator.value),
      (fullClasspath in Runtime).value.map(_.data),
      configFile.value,
      jooqOutputDirectory.value),
    sourceGenerators in Compile += generate.taskValue
  )

  private def generateMetaModel(
    xmlConfig: Option[(Elem, Elem)],
    codeConfig: Option[(Jdbc, Generator)],
    classpath: Seq[File],
    configFile: File,
    outputDirectory: File): Seq[File] = {
    writeConfigFile(xmlConfig, codeConfig, configFile, outputDirectory)
    val fork = new Fork("java", Some("org.jooq.util.GenerationTool"))
    val fullClasspath = classpath :+ configFile.getParentFile
    fork(ForkOptions(bootJars = fullClasspath), Seq("/" + configFile.getName))
    (outputDirectory ** "*.java").get
  }

  private def zip[T, U](option1: Option[T], option2: Option[U]): Option[(T, U)] =
    option1 flatMap { o1 => option2 map { o2 => (o1, o2) } }
}
