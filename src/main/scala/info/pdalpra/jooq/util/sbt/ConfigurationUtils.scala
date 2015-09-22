package info.pdalpra.jooq.util.sbt

import sbt._

import scala.xml.Elem

import info.pdalpra.jooq.util.sbt.model._

import scalaxb.CanWriteXML

object ConfigurationUtils {

  private def marshalToString[T: CanWriteXML](obj: T, label: String) = scalaxb.toXML(obj, label, defaultScope).toString()

  def writeConfigFile(xmlConfig: Option[(Elem, Elem)], codeConfig: Option[(Jdbc, Generator)], configFile: File, outputDirectory: File): Unit = {
    val (jdbc, generator) = xmlConfig match {
      case Some((jdbcXml, generatorXml)) => (scalaxb.fromXML[Jdbc](jdbcXml), scalaxb.fromXML[Generator](generatorXml))
      case None => codeConfig match {
        case Some((jdbcScala, generatorScala)) => (jdbcScala, generatorScala)
        case None                              => throw new IllegalStateException("No configuration, whether through XML or code, is defined for JOOQ's generator.")
      }
    }
    val generatorWithTarget = generator.copy(target = generator.target.map(_.copy(directory = Some(outputDirectory.getAbsolutePath))))
    val configFileContents = marshalToString(jdbc, "jdbc") + marshalToString(generatorWithTarget, "generator")
    val xmlContent = Seq("<configuration>", configFileContents, "</configuration>")
    IO.writeLines(configFile, xmlContent)
  }
}
