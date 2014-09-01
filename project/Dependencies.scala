import sbt._

object Dependencies {

  private val scalaxb = "org.scalaxb" %% "scalaxb" % "1.2.1"

  val pluginDeps = Seq(scalaxb)
}
