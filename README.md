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

## JUnit Runners

### DefaultIntegrationRunner

A more basic implementation for more custom handling - does not handle all JUnit annotations and lifecycles.

### DefaultIntegrationJUnitRunner

An extension of the default JUnit `BlockJUnit4ClassRunner` runner so that it handles and evaluates all JUnit annotations such as `@Ignore`, etc.

## CSV Report Generator

Using the [OpenCSV](https://mvnrepository.com/artifact/com.opencsv/opencsv) library, a default report generator is provided. 

```
# test-application/runApp --evaluate true --number -1
# cat test-application/results.csv 
"Result","Test","Class","Method","Message","Stacktrace"
"SUCCESS","applicationTestNumberProperty","example.test.application.ApplicationTestNumberProperty","testApplicationNumberNotNull","NA","NA"
"FAILED","applicationTestNumberProperty","example.test.application.ApplicationTestNumberProperty","testApplicationNumber","Expecting a value greater than -1 but received -1","java.lang.AssertionError: Expecting a value greater than -1 but received -1
        at org.junit.Assert.fail(Assert.java:88)
        at org.junit.Assert.assertTrue(Assert.java:41)
        at example.test.application.ApplicationTestNumberProperty.testApplicationNumber(ApplicationTestNumberProperty.java:31)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:64)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:564)
        at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
        at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
        at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
        at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
        at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
        at org.junit.runners.Suite.runChild(Suite.java:128)
        at org.junit.runners.Suite.runChild(Suite.java:27)
        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
        at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
        at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
        at org.junit.runner.JUnitCore.run(JUnitCore.java:105)
        at org.junit.runner.JUnitCore.run(JUnitCore.java:94)
        at test.api.DefaultIntegrationLocator.afterPropertiesSet(DefaultIntegrationLocator.java:42)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1863)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1800)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:620)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:542)
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:335)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:333)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:944)
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:918)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:583)
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:754)
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:434)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:338)
        at org.springframework.boot.builder.SpringApplicationBuilder.run(SpringApplicationBuilder.java:143)
        at example.test.application.Application.main(Application.java:20)
"
"SUCCESS","applicationTestNumberProperty","example.test.application.ApplicationTestNumberProperty","testApplicationNumber","NA","NA"
"IGNORED","applicationTestEvaluateProperty","example.test.application.ApplicationTestEvaluateProperty","testEvaluateNotNull","NA","NA"
"SUCCESS","applicationTestEvaluateProperty","example.test.application.ApplicationTestEvaluateProperty","testEvaluate","NA","NA"
```

## References

1. [junit-ext](https://code.google.com/archive/p/junit-ext/)
2. [Conditionally Ignoring Tests in JUnit](https://stackoverflow.com/questions/1689242/conditionally-ignoring-tests-in-junit-4)
3. [Export Data to CSV](https://mkyong.com/java/how-to-export-data-to-csv-file-java/)