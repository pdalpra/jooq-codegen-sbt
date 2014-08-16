package org.jooq.util.sbt

import java.net.URLClassLoader

import sbt._
import sbt.Keys._

import org.jooq.util.GenerationTool
import org.jooq.util.jaxb.{ Configuration => JOOQConfiguration }
import org.jooq.util.sbt.ConfigurationUtils._

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
    regenOnCompile := false,
    jdbcXml := None,
    generatorXml := None,
    jdbc := None,
    generator := None,
    configuration := getConfiguration(zip(jdbcXml.value, generatorXml.value), zip(jdbc.value, generator.value)),
    generate := generateMetaModel((fullClasspath in Runtime).value.map(_.data), configuration.value),
    compile in Compile := { if (regenOnCompile.value) (compile in Compile).dependsOn(generate).value else (compile in Compile).value }
  )

  private def generateMetaModel(classpath: Seq[File], configuration: JOOQConfiguration) = {
    val oldClassLoader = Thread.currentThread().getContextClassLoader

    Thread.currentThread().setContextClassLoader(buildClassLoader(classpath))

    try {
      GenerationTool.main(configuration)
    } finally {
      Thread.currentThread().setContextClassLoader(oldClassLoader)
    }
  }

  private def buildClassLoader(classpath: Seq[File]) =
    new URLClassLoader(classpath.map(_.toURI.toURL).toArray, getClass.getClassLoader)

  private def zip[T, U](option1: Option[T], option2: Option[U]): Option[(T, U)] =
    option1 flatMap { o1 => option2 map { o2 => (o1, o2) } }
}
