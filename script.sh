#!/bin/bash

javac -d out/ src/bankapp/*.java
java -cp out/ bankapp.Main