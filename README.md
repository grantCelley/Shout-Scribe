# Shout Scribe

This is completely free and open source dictation software running on Sphinx 4 speech to text.

# License
The License can be found in the [License file](LICENSE).

The License of the engine can be found at [License of Sphinx](https://github.com/cmusphinx/sphinx4/blob/master/license.terms).

# Install
Download and install [Gradle](https://gradle.org/).
Then run
```
gradle fatJar
```

# Run
To run the project change to the directory build/libs/ folder and run the "Shout Scribe-all-0.5.jar" file or run
```
java -jar "Shout Scribe-all-0.5.jar"
```

### Warnings
- You will get a lot of debug information in the command line if you are running it in the command line.
- You will get an error if you stop the recognition then start again. I am working on it.
