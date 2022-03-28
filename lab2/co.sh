#!/usr/bin/bash
cp ~/apps/idea/lab2/src/main/java/Main.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab2/src/main/java/CmmParser.g4 ~/apps/idea/Lab/src
cp ~/apps/idea/lab2/src/main/java/CmmErrorListener.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab2/src/main/java/CmmErrorStrategy.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab2/src/main/java/CmmWalkListener.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab2/src/main/java/CmmLexer.g4 ~/apps/idea/Lab/src

cd ~/apps/idea/Lab
git add .
git commit -m "$1"
make submit