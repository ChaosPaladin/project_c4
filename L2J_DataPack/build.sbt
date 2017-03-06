name := "datapack"

artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  "datapack." + artifact.extension
}