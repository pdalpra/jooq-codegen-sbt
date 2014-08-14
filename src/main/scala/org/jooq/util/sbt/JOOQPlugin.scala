package org.jooq.util.sbt

import sbt._
import sbt.Keys._

object JOOQPlugin extends AutoPlugin {

  // ---------------------- //
  // -- AutoPlugin setup -- //
  // ---------------------- //

  val autoImport = JOOQKeys

  import autoImport._

  override def projectSettings = JOOQSettings

  // -------------- //
  // -- Settings -- //
  // -------------- //

  val JOOQSettings = Seq(
    regenOnCompile := false,
    compile <<= { if(regenOnCompile.value) compile.dependsOn(generate) else compile }
    
  )

}
