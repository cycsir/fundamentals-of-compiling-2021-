#!/usr/bin/bash
cp ~/apps/idea/lab3/src/main/java/Main.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab3/src/main/java/CmmParser.g4 ~/apps/idea/Lab/src
cp ~/apps/idea/lab3/src/main/java/CmmErrorListener.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab3/src/main/java/CmmLexer.g4 ~/apps/idea/Lab/src

cp ~/apps/idea/lab3/src/main/java/Array.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab3/src/main/java/Float.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab3/src/main/java/Field.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab3/src/main/java/Function.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab3/src/main/java/Int.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab3/src/main/java/Structure.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab3/src/main/java/CmmSemanticVisitor.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab3/src/main/java/Type.java ~/apps/idea/Lab/src
cp ~/apps/idea/lab3/src/main/java/Kind.java ~/apps/idea/Lab/src

cd ~/apps/idea/Lab
git add .
git commit -m "$1"
make submit