#!/bin/bash

# Compile the Java files inside src/bankapp
echo "Compiling Java files in src/bankapp..."

# Check if compilation was successful
if javac src/bankapp/*.java;
then
  echo "Compilation successful."
  cd src
  java bankapp.BankAppLauncher
else
  echo "Compilation failed."
  exit 1
fi