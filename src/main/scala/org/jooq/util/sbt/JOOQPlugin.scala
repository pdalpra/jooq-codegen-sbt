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

  override def requires = plugins.JvmPlugin

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
    showGenerationLog := true,
    configFile := target.value / "jooq" / "jooq-config.xml",
    generate := generateMetaModel(
      zip(jdbcXml.value, generatorXml.value),
      zip(jdbc.value, generator.value),
      (dependencyClasspath in Compile).value.map(_.data),
      configFile.value,
      jooqOutputDirectory.value,
      showGenerationLog.value),
    sourceGenerators in Compile += generate.taskValue
  )

  private def generateMetaModel(
    xmlConfig: Option[(Elem, Elem)],
    codeConfig: Option[(Jdbc, Generator)],
    classpath: Seq[File],
    configFile: File,
    outputDirectory: File,
    showLog: Boolean): Seq[File] = {

    writeConfigFile(xmlConfig, codeConfig, configFile, outputDirectory)

    val dependencyClasspath = (classpath :+ configFile.getParentFile).map(_.getAbsolutePath).mkString(sys.props("path.separator"))
    val command = Seq("java", "-cp", dependencyClasspath, "org.jooq.util.GenerationTool", s"/${configFile.getName}")
    val process = Process(command)

    if (showLog) process.! else process.!(NullProcessLogger)
    (outputDirectory ** "*.java").get
  }

  private def zip[T, U](option1: Option[T], option2: Option[U]): Option[(T, U)] =
    option1 flatMap { o1 => option2 map { o2 => (o1, o2) } }

  private val NullProcessLogger = new ProcessLogger {
    override def error(s: => String): Unit = ()
    override def buffer[T](f: => T): T = f
    override def info(s: => String): Unit = ()
  }
}
