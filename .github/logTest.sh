#!/usr/bin/env bash

# old Task:
#./gradlew connectedCheck
#      - name: Output Crash
#        run: cat app/build/reports/androidTests/connected/flavors/debugAndroidTest/index.html

# News Task from: https://github.com/kiwix/kiwix-android/blob/develop/contrib/instrumentation_nightly.sh

adb logcat -c
adb logcat *:E -v color &
if ./gradlew connectedDebugAndroidTest; then
  echo "connectedDebugAndroidTest succeeded" >&2
  exit 1
fi
