package cz.library.store.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class StringUtil {

  public static String camelCaseToWords(String str) {
    return str
        .replaceAll("([a-z])([A-Z])", "$1 $2")
        .replaceAll("([a-zA-Z])([0-9])", "$1 $2")
        .replaceAll("([0-9])([a-zA-Z])", "$1 $2")
        .toLowerCase();
  }

  public static String concatListOr(List<String> words) {
    if(words.size() <= 1) {
      return words.stream()
          .collect(Collectors.joining());
    }

    return words.subList(0, words.size() - 1).stream()
        .collect(Collectors.joining(", "))
        .toString() + " or " + words.get(words.size() - 1);
  }

  public static String capitalizeWords(String str) {
    if (str.length() <= 1) {
      return str.toLowerCase();
    }

    return Arrays.asList(str.split(" ")).stream()
        .map(word -> word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase())
        .collect(Collectors.joining(" "));
  }

}
