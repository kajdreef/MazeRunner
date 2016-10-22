all: run

run: 
	mvn compile exec:java -Dexec.mainClass=io.github.kajdreef.mazerunner.Launcher

.PHONY:
init:
	mvn clean
	mvn package
	export MAVEN_OPTS=-Djava.library.path=target/natives

.PHONY:
clean:
	mvn clean

.PHONY:
test:
	mvn test
