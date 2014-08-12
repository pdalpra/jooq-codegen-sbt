import sbt._
import sbt.Keys._

import BuildSettings._
import Dependencies._

object JooqPluginBuild extends Build {
	
  override lazy val settings = super.settings ++ {
    shellPrompt := { state => Project.extract(state).currentProject.id + " > " }
  }

  /*******************/
  /** Bench Project **/
  /*******************/

  lazy val root = Project("jooq-codegen-sbt", file("."))
    .settings(jooqPluginSettings: _*)
    .settings(libraryDependencies ++= allDeps)

}