package org.testdouble;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class App {
  public static void main(String[] args) throws Exception {
    final String fileName = args[0];
    final List<String> lines = Files.readAllLines(Paths.get(fileName));
    final List<String> accountNumbers = new InputFile(lines).toAccountNumbers();
    final OutputFile outputFile = new OutputFile(accountNumbers.stream()
            .map(AccountNumber::new)
            .map(AccountNumber::toString)
            .collect(Collectors.toList()));

    System.out.println(outputFile);
  }
}
