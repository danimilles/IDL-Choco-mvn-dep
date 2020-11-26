# IDL-mvn-dep
The purpose of this repo is to offer the Inter-parameter Dependency Language (IDL) as a Maven project, which can be compiled as a JAR or included as a Maven dependency into other projects.

In order to install the dependency locally, run the following command from the root directory:
```
mvn install:install-file -Dfile=es.us.isa.idl.parent/es.us.isa.idl/target/idl-0.0.1-SNAPSHOT.jar -DgroupId=es.us.isa -DartifactId=idl -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar
```
