import sbt._

object Dependencies {

  def jooq(version: String)     = "org.jooq" % "jooq"      % version
  def jooqMeta(version: String) = "org.jooq" % "jooq-meta" % version

  def pluginDeps(version: String) = Seq(jooq _, jooqMeta _).map(_(version))
}
