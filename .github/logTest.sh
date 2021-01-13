#!/usr/bin/env bash

# old Task:
#./gradlew connectedCheck
#      - name: Output Crash
#        run: cat app/build/reports/androidTests/connected/flavors/debugAndroidTest/index.html

# News Task from: https://github.com/kiwix/kiwix-android/blob/develop/contrib/instrumentation_nightly.sh

adb logcat -c
adb logcat *:E -v color &
if ./gradlew connectedCheck; then
  echo "connectedDebugAndroidTest succeeded" >&2
else
  adb logcat -v brief output # -d
  # cat app/build/reports/androidTests/connected/flavors/debugAndroidTest/index.html
  exit 1
fi
