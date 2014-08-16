package org.jooq.util.sbt

import javax.xml.bind.JAXB

import scala.xml.Elem

import org.jooq.util.jaxb.{ Configuration => JOOQConfiguration, Generator => JOOQGenerator, Jdbc => JOOQJdbc }
import org.jooq.util.sbt.model._

object ConfigurationUtils {

  def getConfiguration(xmlConfig: Option[(Elem, Elem)], codeConfig: Option[(Jdbc, Generator)]) = {
    val (jdbcXmlString, generatorXmlString) = xmlConfig match {
      case Some((jdbcXml, generatorXml)) => (jdbcXml.toString, generatorXml.toString)
      case None => codeConfig match {
        case Some((jdbc, generator)) =>
          (scalaxb.toXML(jdbc, "jdbc", defaultScope).toString, scalaxb.toXML(generator, "generator", defaultScope).toString)
        case None =>
          throw new IllegalStateException("No configuration, whether through XML or code, is defined for JOOQ's generator.")
      }
    }
    val jooqJdbc = JAXB.unmarshal(jdbcXmlString, classOf[JOOQJdbc])
    val jooqGenerator = JAXB.unmarshal(generatorXmlString, classOf[JOOQGenerator])

    val configuration = new JOOQConfiguration
    configuration.setJdbc(jooqJdbc)
    configuration.setGenerator(jooqGenerator)
    configuration
  }
}
