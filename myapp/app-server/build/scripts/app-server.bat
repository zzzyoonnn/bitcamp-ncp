@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  app-server startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and APP_SERVER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\app-server-0.0.1-SNAPSHOT-plain.jar;%APP_HOME%\lib\spring-boot-devtools-3.0.4.jar;%APP_HOME%\lib\mybatis-spring-boot-starter-3.0.0.jar;%APP_HOME%\lib\spring-boot-starter-jdbc-3.0.4.jar;%APP_HOME%\lib\spring-boot-starter-web-3.0.4.jar;%APP_HOME%\lib\spring-boot-starter-undertow-3.0.4.jar;%APP_HOME%\lib\spring-cloud-starter-aws-2.4.4.jar;%APP_HOME%\lib\mariadb-java-client-3.0.10.jar;%APP_HOME%\lib\spring-boot-starter-json-3.0.4.jar;%APP_HOME%\lib\spring-boot-starter-3.0.4.jar;%APP_HOME%\lib\mybatis-spring-boot-autoconfigure-3.0.0.jar;%APP_HOME%\lib\spring-cloud-aws-autoconfigure-2.4.4.jar;%APP_HOME%\lib\spring-boot-autoconfigure-3.0.4.jar;%APP_HOME%\lib\spring-boot-3.0.4.jar;%APP_HOME%\lib\HikariCP-5.0.1.jar;%APP_HOME%\lib\spring-jdbc-6.0.6.jar;%APP_HOME%\lib\spring-webmvc-6.0.6.jar;%APP_HOME%\lib\spring-web-6.0.6.jar;%APP_HOME%\lib\undertow-websockets-jsr-2.3.4.Final.jar;%APP_HOME%\lib\undertow-servlet-2.3.4.Final.jar;%APP_HOME%\lib\undertow-core-2.3.4.Final.jar;%APP_HOME%\lib\tomcat-embed-el-10.1.5.jar;%APP_HOME%\lib\mybatis-3.5.11.jar;%APP_HOME%\lib\mybatis-spring-3.0.0.jar;%APP_HOME%\lib\spring-cloud-aws-context-2.4.4.jar;%APP_HOME%\lib\spring-cloud-aws-core-2.4.4.jar;%APP_HOME%\lib\spring-boot-starter-logging-3.0.4.jar;%APP_HOME%\lib\logback-classic-1.4.5.jar;%APP_HOME%\lib\log4j-to-slf4j-2.19.0.jar;%APP_HOME%\lib\jul-to-slf4j-2.0.6.jar;%APP_HOME%\lib\slf4j-api-2.0.6.jar;%APP_HOME%\lib\spring-context-6.0.6.jar;%APP_HOME%\lib\spring-tx-6.0.6.jar;%APP_HOME%\lib\spring-aop-6.0.6.jar;%APP_HOME%\lib\spring-beans-6.0.6.jar;%APP_HOME%\lib\spring-expression-6.0.6.jar;%APP_HOME%\lib\spring-core-6.0.6.jar;%APP_HOME%\lib\jakarta.annotation-api-2.1.1.jar;%APP_HOME%\lib\snakeyaml-1.33.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.14.2.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.14.2.jar;%APP_HOME%\lib\jackson-annotations-2.14.2.jar;%APP_HOME%\lib\aws-java-sdk-s3-1.12.395.jar;%APP_HOME%\lib\aws-java-sdk-ec2-1.12.395.jar;%APP_HOME%\lib\aws-java-sdk-kms-1.12.395.jar;%APP_HOME%\lib\aws-java-sdk-core-1.12.395.jar;%APP_HOME%\lib\jackson-dataformat-cbor-2.14.2.jar;%APP_HOME%\lib\jackson-core-2.14.2.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.14.2.jar;%APP_HOME%\lib\jmespath-java-1.12.395.jar;%APP_HOME%\lib\jackson-databind-2.14.2.jar;%APP_HOME%\lib\micrometer-observation-1.10.4.jar;%APP_HOME%\lib\xnio-nio-3.8.8.Final.jar;%APP_HOME%\lib\xnio-api-3.8.8.Final.jar;%APP_HOME%\lib\wildfly-client-config-1.0.1.Final.jar;%APP_HOME%\lib\jboss-threads-3.5.0.Final.jar;%APP_HOME%\lib\jboss-logging-3.5.0.Final.jar;%APP_HOME%\lib\jakarta.servlet-api-6.0.0.jar;%APP_HOME%\lib\jakarta.websocket-api-2.1.0.jar;%APP_HOME%\lib\jakarta.websocket-client-api-2.1.0.jar;%APP_HOME%\lib\spring-jcl-6.0.6.jar;%APP_HOME%\lib\micrometer-commons-1.10.4.jar;%APP_HOME%\lib\wildfly-common-1.5.4.Final.jar;%APP_HOME%\lib\logback-core-1.4.5.jar;%APP_HOME%\lib\log4j-api-2.19.0.jar;%APP_HOME%\lib\httpclient-4.5.14.jar;%APP_HOME%\lib\commons-codec-1.15.jar;%APP_HOME%\lib\ion-java-1.0.2.jar;%APP_HOME%\lib\joda-time-2.8.1.jar;%APP_HOME%\lib\httpcore-4.4.16.jar


@rem Execute app-server
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %APP_SERVER_OPTS%  -classpath "%CLASSPATH%" bitcamp.myapp.App %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable APP_SERVER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%APP_SERVER_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
