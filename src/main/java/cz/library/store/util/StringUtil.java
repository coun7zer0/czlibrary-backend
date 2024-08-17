package cz.library.store.util;

import java.util.List;
import java.util.stream.Collectors;

public final class StringUtil {
  
  public static String camelCaseToWords(String str) {
    return str
        .replaceAll("([a-z])([A-Z])", "$1 $2")
        .toLowerCase();
  }

  public static String concatListOr(List<String> words) {
    if(words.size() == 1) {
      return words.get(0);
    }
    
    return words.subList(0, words.size() - 1).stream()
        .collect(Collectors.joining(", "))
        .toString() + " or " + words.get(words.size() - 1);
  }

}
