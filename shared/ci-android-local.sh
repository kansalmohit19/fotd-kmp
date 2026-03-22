#!/bin/bash

set -e

echo "🚀 CI START"

# Java check
java -version

# Gradle version
./gradlew -v

# Clean
./gradlew clean

# Parallel build for speed
export GRADLE_OPTS="-Dorg.gradle.parallel=true"

# Build shared (KMP)
./gradlew :shared:build --parallel

# Run tests
./gradlew test --continue

# Lint
./gradlew :androidApp:lint

# Assemble APK
./gradlew :androidApp:assembleDebug

echo "🎉 CI SUCCESS"