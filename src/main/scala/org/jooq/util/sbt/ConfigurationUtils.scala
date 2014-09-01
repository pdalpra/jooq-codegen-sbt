package org.jooq.util.sbt

import sbt._

import scala.xml.Elem

import org.jooq.util.sbt.model._

object ConfigurationUtils {

  def writeConfigFile(xmlConfig: Option[(Elem, Elem)], codeConfig: Option[(Jdbc, Generator)], configFile: File): Unit = {
    val (jdbcXmlString, generatorXmlString) = xmlConfig match {
      case Some((jdbcXml, generatorXml)) => (jdbcXml.toString, generatorXml.toString)
      case None => codeConfig match {
        case Some((jdbc, generator)) =>
          (scalaxb.toXML(jdbc, "jdbc", defaultScope).toString, scalaxb.toXML(generator, "generator", defaultScope).toString)
        case None =>
          throw new IllegalStateException("No configuration, whether through XML or code, is defined for JOOQ's generator.")
      }
    }

    IO.delete(configFile)
    IO.touch(configFile)
    val xmlContent = Seq("<configuration>", jdbcXmlString, generatorXmlString, "</configuration>")
    IO.writeLines(configFile, xmlContent, append = true)
  }
}
