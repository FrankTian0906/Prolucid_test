# Prolucid_test

* This app is used to detect sentences in the article and sort the sentences by alphabet order
* The app is based on `Java 1.8v` and `openNLP` API environment;
* Please use `Maven` to build the project(pom.xml) for uploading Java 1.8 and OpenNLP; 

#Install

* Please download the project by Git
    * git clone https://github.com/FrankTian0906/Prolucid_test.git
* Please open the project by any IDE(Eclipse, VS Code, IntelliJ IDEA, etc) and run App.class

#Structure

* `src/main/java/App.class` main file for running
* `src/main/java/SentenceDetectorTraining` for training and producing `en-sent-custom.bin` file

* `src/main/java/nlpbin/en-sent.bin` official trained bin file
* `src/main/java/nlpbin/en-sent-custom.bin` personal trained bin file

* `src/main/resources/trainingDataSentences.txt` is the material for training used in SentenceDetectorTraining
* `src/main/resources/ShortStory.txt` the target file used in App