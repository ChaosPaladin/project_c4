val libDir = file("lib")

unmanagedJars in Compile ++= Seq(
		libDir / "c3p0-0.9.0.jar",
		libDir / "bsf.jar",
		libDir / "bsh-2.0.jar",
		libDir / "jython.jar",
		libDir / "javolution.jar",
		libDir / "hibernate3.jar"
	)
	
javaSource in Compile := baseDirectory.value / "java"

artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  "l2jserver." + artifact.extension
}	