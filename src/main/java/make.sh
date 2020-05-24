#!/bin/sh

# TODO
find -name '*.java' | xargs javac -Xlint:all
java com.codecool.enigma.Enigma $1 $2 $3 $4