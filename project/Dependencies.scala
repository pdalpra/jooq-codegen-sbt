import sbt._

object Dependencies {

  private val scalaxb                      = "org.scalaxb" %% "scalaxb"      % "1.2.1"
  private def jooqCodegen(version: String) = "org.jooq"     % "jooq-codegen" % version

  def pluginDeps(version: String) = Seq(jooqCodegen _).map(_(version)) :+ scalaxb
}
