git pull
mvn package -Dquarkus.package.type=uber-jar
cp ./target/weblog-1.0-SNAPSHOT-runner.jar ~/apps
java -jar ~/apps/weblog-1.0-SNAPSHOT-runner.jar