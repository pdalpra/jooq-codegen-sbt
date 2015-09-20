organization  := "org.jooq"
startYear     := Some(2014)
sbtPlugin     := true
scalacOptions := Seq(
  "-encoding", "UTF-8",
  "-target:jvm-1.6",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-language:existentials"
)

libraryDependencies += "org.scalaxb" %% "scalaxb" % "1.4.0"

scalaxbSettings

ScalaxbKeys.packageName in (Compile, ScalaxbKeys.scalaxb) := "org.jooq.util.sbt.model"
sourceGenerators in Compile += (ScalaxbKeys.scalaxb in Compile).taskValue
