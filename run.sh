#!/bin/bash

# Clean previous results
echo "Cleaning previous results..."
rm -rf bin
mkdir -p bin

# Compile packages
echo "Compiling Java resources..."
javac -cp "src:lib/*" -d bin src/banking/*.java src/tests/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful."
else
    echo "Compilation failed."
    exit 1
fi
# Run all tests with JUnit 5
echo "Running tests..."
java -jar lib/junit-platform-console-standalone-1.12.0.jar --class-path bin --scan-classpath

# Check if tests passed
if [ $? -eq 0 ]; then
    echo "Tests ran successfully."
else
    echo "Tests failed."
    exit 1
fi
# Run the Menu class
echo "Running the program..."
java -cp bin banking.BankingApp

