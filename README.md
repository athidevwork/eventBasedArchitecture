# eventBasedArchitecture

## parent-project
mvn archetype:generate -DgroupId=org.athi -DartifactId=parent-project
## foundation
mvn archetype:generate -DgroupId=org.athi.eba -DartifactId=foundation -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.0 -DinteractiveMode=false
## client
mvn archetype:generate -DgroupId=com.athi.eba -DartifactId=client -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.0 -DinteractiveMode=false
## order
mvn archetype:generate -DgroupId=com.athi.eba -DartifactId=order -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.0 -DinteractiveMode=false
## payment
mvn archetype:generate -DgroupId=com.athi.eba -DartifactId=payment -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.0 -DinteractiveMode=false


