import sbt._
import sbt.Keys._

import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import net.virtualvoid.sbt.graph.Plugin.graphSettings

object BuildSettings {

  /********************/
  /** Basic settings **/
  /********************/

  lazy val basicSettings = Seq(
    organization  := "org.jooq",
    startYear     := Some(2014),
    scalaVersion  := "2.10.4",
    sbtPlugin     := true,
    scalacOptions := Seq(
      "-encoding", "UTF-8",
      "-target:jvm-1.6",
      "-deprecation",
      "-feature",
      "-unchecked"
    )
  )

  lazy val jooqPluginSettings =
    basicSettings ++ formattingSettings ++ graphSettings ++ Release.settings

  /*************************/
  /** Formatting settings **/
  /*************************/

  lazy val formattingSettings = SbtScalariform.scalariformSettings ++ Seq(
    ScalariformKeys.preferences := formattingPreferences
  )

  import scalariform.formatter.preferences._

  def formattingPreferences =
    FormattingPreferences()
      .setPreference(DoubleIndentClassDeclaration, true)
      .setPreference(AlignParameters, true)
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(IndentLocalDefs, true)
      .setPreference(PreserveDanglingCloseParenthesis, true)
}
