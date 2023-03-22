mvn clean test package

java -cp target/bank-ocr-kata-1.0-SNAPSHOT.jar org.testdouble.App "$(pwd)/input.txt"
