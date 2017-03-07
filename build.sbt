lazy val commonSettings = Seq(
	name := "la2",
	organization := "sart",
	version := "0.0.1",
	autoScalaLibrary := false,
	crossPaths := false,
	javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-encoding", "UTF-8")
	//publishMavenStyle := false
)

lazy val root = (project in file(".")).
	settings(commonSettings).
	aggregate(core, dp)
	
lazy val core = (project in file("L2J_Server")).
	settings(commonSettings)
	
lazy val dp = (project in file("L2J_DataPack")).
	settings(commonSettings)