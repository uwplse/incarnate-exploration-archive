#!/usr/bin/env bash

for g in $(find ../.. -name '*.gcode'); do
  printf "Analyzing %s\n" "$g"
  java GCodeAnalyzer "$g"
  echo
done
