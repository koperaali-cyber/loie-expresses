@REM Apache Maven Wrapper (script-only) for Windows
@echo off
set MAVEN_VERSION=3.9.9
set WRAPPER_DIR=%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MAVEN_VERSION%
set MVN_CMD=%WRAPPER_DIR%\apache-maven-%MAVEN_VERSION%\bin\mvn.cmd
if not exist "%MVN_CMD%" (
  echo Downloading Maven %MAVEN_VERSION%...
  mkdir "%WRAPPER_DIR%" 2>nul
  powershell -Command "Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MAVEN_VERSION%/apache-maven-%MAVEN_VERSION%-bin.zip' -OutFile '%WRAPPER_DIR%\maven.zip'"
  powershell -Command "Expand-Archive -Path '%WRAPPER_DIR%\maven.zip' -DestinationPath '%WRAPPER_DIR%' -Force"
  del "%WRAPPER_DIR%\maven.zip"
)
"%MVN_CMD%" %*
