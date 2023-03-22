package org.testdouble;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App 
{
    public static void main( String[] args ) throws Exception {
        final String fileName = args[0];
        final List<String> lines = Files.readAllLines(Paths.get(fileName));
        final List<AccountNumber> accountNumbers = new InputFile(lines).toAccountNumbers();
        System.out.println(new OutputFile(accountNumbers));
    }
}
