package cz.library.store.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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

    // When
    String result = StringUtil.camelCaseToWords(camelCase);

    // Then
    assertEquals("camel case with 2 numbers 34", result);
  }

  @Test
  void givenCapitalizedCamelCase_whenConvertToWords_thenReturnsSpaceSeparatedWordsInLowerCase() {
    // Given
    String camelCase = "CapitalizedCamelCase";

    // When
    String result = StringUtil.camelCaseToWords(camelCase);

    // Then
    assertEquals("capitalized camel case", result);
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

  @Test
  void givenEmptyList_whenConcatListOr_thenReturnsEmptyString() {
    // Given
    List<String> emptyList = new ArrayList<>();

    // When
    String result = StringUtil.concatListOr(emptyList);

    // Then
    assertEquals("", result);
  }

  @Test
  void givenLowercaseString_whenCapitalizeWords_thenReturnsCapitalizedString() {
    // Given
    String lowercaseString = "apple banana cherry";

    // When
    String result = StringUtil.capitalizeWords(lowercaseString);

    // Then
    assertEquals("Apple Banana Cherry", result);
  }

  @Test
  void givenUppercaseString_whenCapitalizeWords_thenReturnsCapitalizedString() {
    // Given
    String uppercaseString = "APPLE BANANA CHERRY";

    // When
    String result = StringUtil.capitalizeWords(uppercaseString);

    // Then
    assertEquals("Apple Banana Cherry", result);
  }

  @Test
  void givenMixedCaseString_whenCapitalizeWords_thenReturnsCapitalizedString() {
    // Given
    String mixedCaseString = "aPpLe BaNaNa ChErRy";

    // When
    String result = StringUtil.capitalizeWords(mixedCaseString);

    // Then
    assertEquals("Apple Banana Cherry", result);
  }

  @Test
  void givenEmptyString_whenCapitalizeWords_thenReturnsEmptyString() {
    // Given
    String emptyString = "";

    // When
    String result = StringUtil.capitalizeWords(emptyString);

    // Then
    assertEquals("", result);
  }

}
