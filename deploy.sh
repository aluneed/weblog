git pull
mvn package -Dquarkus.package.type=uber-jar
cp ./target/weblog-1.0-SNAPSHOT-runner.jar ~/apps
kill -15 $(ps aux | grep weblog-1.0-SNAPSHOT-runner.jar | awk '{print $2}')
java -jar ~/apps/weblog-1.0-SNAPSHOT-runner.jar