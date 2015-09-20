scriptedSettings

scriptedLaunchOpts ++= Seq("-Xmx512m", "-XX:MaxPermSize=256m", "-Dplugin.version=" + version.value)
scriptedBufferLog   := true