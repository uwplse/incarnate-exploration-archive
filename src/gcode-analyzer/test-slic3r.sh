#!/usr/bin/env bash

# find all stl in repo, slice it, then test gcode
for s in $(find ../.. -name '*.stl'); do
  g="$(mktemp -t $(basename "$s" .stl)).gcode"

  printf "Slicing %s\n" "$s"
  slic3r "$s" -o "$g"

  printf "Analyzing %s\n" "$s"
  java GCodeAnalyzer "$g"

  # clean up
  rm -f "$g"
  echo
done
