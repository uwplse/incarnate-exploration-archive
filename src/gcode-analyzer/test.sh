#!/usr/bin/env bash

# NOTE! assumes user runs from this dir

for g in $(find ../.. -name '*.gcode'); do
  printf "Analyzing %s\n" "$g"
  java GCodeAnalyzer "$g"
  echo
done
