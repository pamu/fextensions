name := "fextensions"

version := "0.0.1-v1-SNAPSHOT"

scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.11.8", "2.12.0")

libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-actor" % "2.4.13")

libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.0.0" % "test")
