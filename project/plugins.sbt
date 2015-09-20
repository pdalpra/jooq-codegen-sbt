addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.5.1")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.0")
addSbtPlugin("org.scalaxb" % "sbt-scalaxb" % "1.4.0")
addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")

libraryDependencies += "org.scala-sbt" % "scripted-plugin" % sbtVersion.value
