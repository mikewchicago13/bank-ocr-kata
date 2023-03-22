package org.testdouble;

import java.util.List;
import java.util.stream.Collectors;

public class OutputFormatting<T> {
  private final List<T> lines;

  public OutputFormatting(List<T> lines) {
    this.lines = lines;
  }

  @Override
  public String toString() {
    return lines.stream().map(Object::toString).collect(Collectors.joining(System.lineSeparator()));
  }
}
