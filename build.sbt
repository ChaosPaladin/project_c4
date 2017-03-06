lazy val commonSettings = Seq(
	name := "la2",
	organization := "sart",
	version := "0.0.1",
	autoScalaLibrary := false,
	crossPaths := false
	//publishMavenStyle := false
)

lazy val root = (project in file(".")).
	settings(commonSettings).
	aggregate(core, dp)
	
lazy val core = (project in file("L2J_Server")).
	settings(commonSettings)
	
lazy val dp = (project in file("L2J_DataPack")).
	settings(commonSettings)