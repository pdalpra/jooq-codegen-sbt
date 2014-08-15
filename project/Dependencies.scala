import sbt._

object Dependencies {

  def jooqCodegen(version: String)     = "org.jooq" % "jooq-codegen" % version

  def pluginDeps(version: String) = Seq(jooqCodegen _).map(_(version))
}
