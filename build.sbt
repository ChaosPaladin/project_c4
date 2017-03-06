lazy val commonSettings = Seq(
	name := "la2",
	organization := "sart",
	version := "0.0.0",
	autoScalaLibrary := false,
	crossPaths := false
	//publishMavenStyle := false
)

lazy val makeZip taskKey[Unit]("Create zip")

lazy val root = (project in file(".")).
	settings(commonSettings).
	aggregate(core)
	
lazy val core = (project in file("L2J_Server")).
	settings(commonSettings)