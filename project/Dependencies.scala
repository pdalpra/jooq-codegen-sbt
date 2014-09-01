import sbt._

object Dependencies {

  private val scalaxb = "org.scalaxb" %% "scalaxb" % "1.2.1"

  def pluginDeps(version: String) = Seq(scalaxb)
}
