package cz.library.store.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class StringUtilTest {

  @Test
    void givenCamelCaseString_whenConvertToWords_thenReturnsSpaceSeparatedWords() {
    // Given
    String camelCase = "camelCaseToWords";

    // When
    String result = StringUtil.camelCaseToWords(camelCase);

    // Then
    assertEquals("camel case to words", result);
  }

  @Test

  void givenCamelCaseWithNumbers_whenConvertToWords_thenReturnsSpaceSpecifiedWords() {
    // Given
    String camelCase = "camelCaseWith2Numbers34";

    // when
    String result = StringUtil.camelCaseToWords(camelCase);

    assertEquals("camel case with 2 numbers 34", result);
  }

  

  @Test
    void givenSingleElementList_whenConcatListOr_thenReturnsSingleElement() {
    // Given
    List<String> singleElementList = Arrays.asList("apple");

    // When
    String result = StringUtil.concatListOr(singleElementList);

    // Then
    assertEquals("apple", result);
  }

  @Test
    void givenMultipleElementsList_whenConcatListOr_thenReturnsCommaSeparatedWithOr() {
    // Given
    List<String> multipleElementList = Arrays.asList("apple", "banana", "cherry");

    // When
    String result = StringUtil.concatListOr(multipleElementList);

    // Then
    assertEquals("apple, banana or cherry", result);
  }

  @Test
    void givenTwoElementsList_whenConcatListOr_thenReturnsElementsSeparatedWithOr() {
    // Given
    List<String> twoElementList = Arrays.asList("apple", "banana");

    // When
    String result = StringUtil.concatListOr(twoElementList);

    // Then
    assertEquals("apple or banana", result);
  }
}
