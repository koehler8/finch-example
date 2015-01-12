name := "finch-example"

version := "1.0"

scalaVersion := "2.11.5"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "com.github.finagle" %% "finch-json" % "0.4.0-SNAPSHOT" changing()

