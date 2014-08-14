package org.jooq.util.sbt

import sbt._

object JOOQKeys {

  val generate = taskKey[Unit]("Generate JOOQ metamodel")
  val regenOnCompile = settingKey[Boolean]("Regenerate metamodel when compiling")
}
