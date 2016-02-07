#!/usr/bin/env bash

# NOTE! assumes user runs from this dir

# find all stl in repo, slice it, then test gcode
for s in $(find ../.. -name '*.stl'); do
  g="$(mktemp -t $(basename "$s" .stl)).gcode"

  printf "Slicing %s\n" "$s"
  slic3r "$s" -o "$g"

  if [ $? -ne 0 ]; then
    echo
    echo !!! ERROR !!!
    echo "$s" did not correctly slice
    exit 1
  fi

  printf "Analyzing %s\n" "$s"
  java GCodeAnalyzer "$g"

  if [ $? -ne 0 ]; then
    echo
    echo !!! ERROR !!!
    echo gcode from "$s" did not pass analyzer
    exit 1
  fi

  # clean up
  rm -f "$g"
  echo
done
