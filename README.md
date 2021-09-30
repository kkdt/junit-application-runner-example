# junit-application-runner-example

> Opinionated approach to integration testing using JUnit and Spring

## Overview

The module `test-api` is the prototype for supporting integration tests within a Spring application; the module `test-application` is the application that is executed and contains the `@Test` routines.

## Quickstart

- Build (use the appropriate gradlew script for Linux vs. Windows)

```
./gradlew clean build
```

- Unpack the application bundle to a location, i.e. `$HOME` (Linux)

```
tar --directory=$HOME -xvf test-application/build/distributions/test-application.tar
```

- Run the application to see integration tests run; use different values for the options to manipulate test results

```
$HOME/test-application/runApp --evaluate true --number 1
```