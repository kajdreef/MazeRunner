# MazeRunner

This will be a 3D game created using Java and LWJGL.

## Running the game:

To be able to run the game you need to have maven installed.

```
$ mvn clean
$ mvn package
```

Then depending on if you run an unix or windows you run:

**UNIX:**
```
$ export MAVEN_OPTS=-Djava.library.path=target/natives
```

**WINDOWS:**
```
$ set MAVEN_OPTS="-Djava.library.path=target/natives"
```

After this run:

```
$ mvn compile exec:java -Dexec.mainClass=io.github.kajdreef.mazerunner.Launcher
```
