import sbt._
import sbt.Keys._

import sbtscalaxb.Plugin._
import sbtscalaxb.Plugin.ScalaxbKeys._
object ModelGen {

  val modelGenSettings = scalaxbSettings ++ Seq(
    packageName in (Compile, scalaxb) := "org.jooq.util.sbt.model",
    sourceGenerators in Compile <+= (scalaxb in Compile)
  )
}
