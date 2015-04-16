name := "eXceedVote"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaCore,
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "org.mariadb.jdbc" % "mariadb-java-client" % "1.1.8",
  "mysql" % "mysql-connector-java" % "5.1.18"
)