#!/usr/bin/env bash
set -e

echo "Running local CI validation..."

cd ..
./gradlew assembleQaDebug

APK=$(ls androidApp/build/outputs/apk/qa/debug/androidApp-qa-debug.apk | head -n 1)

if [[ ! -f "$APK" ]]; then
  echo "❌ APK not found"
  exit 1
fi

echo "✅ APK found: $APK"