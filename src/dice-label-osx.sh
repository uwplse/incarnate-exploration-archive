#!/usr/bin/env bash

if [ "$#" -ne 3 ]; then
  echo "Usage: $0 START STOP IMGDIR"
  exit 1
fi

START="$1"
STOP="$2"
IMGDIR="$3"

if ! [ -d "$IMGDIR" ]; then
  echo "ERROR: $IMGDIR not a directory!"
  exit 1
fi

for i in $(seq $START $STOP); do
  img="$IMGDIR/$i.jpeg"
  if ! [ -f "$img" ]; then
    echo "WARNING: could not find $img"
    continue
  fi

  open -a Preview -g "$img"
  read -n 1 f
  osascript <<EOF
tell application "Preview" to close window 1
EOF

  echo "$i,$f" >> "${USER}-dice-faces.csv"
done
