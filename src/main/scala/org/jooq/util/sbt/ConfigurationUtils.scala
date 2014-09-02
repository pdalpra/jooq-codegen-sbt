package org.jooq.util.sbt

import sbt._

import scala.xml.Elem

import org.jooq.util.sbt.model._

import scalaxb.CanWriteXML

object ConfigurationUtils {

  private def marshalToString[T: CanWriteXML](obj: T, label: String) = scalaxb.toXML(obj, label, defaultScope).toString()

  def writeConfigFile(xmlConfig: Option[(Elem, Elem)], codeConfig: Option[(Jdbc, Generator)], configFile: File): Unit = {
    val configFileContents = xmlConfig match {
      case Some((jdbcXml, generatorXml)) => jdbcXml.toString + generatorXml.toString
      case None => codeConfig match {
        case Some((jdbc, generator)) =>
          marshalToString(jdbc, "jdbc") + marshalToString(generator, "generator")
        case None =>
          throw new IllegalStateException("No configuration, whether through XML or code, is defined for JOOQ's generator.")
      }
    }

    val xmlContent = Seq("<configuration>", configFileContents, "</configuration>")
    IO.writeLines(configFile, xmlContent)
  }
}
