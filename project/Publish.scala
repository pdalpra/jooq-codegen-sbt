import sbt.Keys._

import bintray.Plugin.bintrayPublishSettings
import bintray.Keys._

object Publish {

  lazy val publishSettings = bintrayPublishSettings ++ Seq(
    publishMavenStyle := false,
    bintrayOrganization in bintray := None,
    repository in bintray := "sbt-plugins"
  )
}
