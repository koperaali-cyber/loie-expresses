#!/bin/sh
# Apache Maven Wrapper startup script (script-only). Downloads Maven if absent.
set -e
MAVEN_VERSION=3.9.9
WRAPPER_DIR="$HOME/.m2/wrapper/dists/apache-maven-$MAVEN_VERSION"
DIST_URL="https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$MAVEN_VERSION/apache-maven-$MAVEN_VERSION-bin.zip"
MVN_CMD="$WRAPPER_DIR/apache-maven-$MAVEN_VERSION/bin/mvn"
if [ ! -x "$MVN_CMD" ]; then
  echo "Downloading Maven $MAVEN_VERSION..."
  mkdir -p "$WRAPPER_DIR"
  TMP_ZIP="$WRAPPER_DIR/maven.zip"
  if command -v curl >/dev/null 2>&1; then curl -fsSL -o "$TMP_ZIP" "$DIST_URL";
  else wget -q -O "$TMP_ZIP" "$DIST_URL"; fi
  unzip -q "$TMP_ZIP" -d "$WRAPPER_DIR"
  rm -f "$TMP_ZIP"
fi
exec "$MVN_CMD" "$@"
