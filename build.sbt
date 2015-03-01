name := "eXceedVote"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.7.2"

libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs
)

