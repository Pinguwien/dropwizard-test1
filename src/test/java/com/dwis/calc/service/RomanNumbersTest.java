package com.dwis.calc.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dominik on 03.04.16.
 */
public class RomanNumbersTest {

    private RomanNumbers romanNumberParser = new RomanNumbers();

    //ok, this is definitely not a unit test for one unit only. more to come.
    @Test
    public void checkParsingFromRomanAndToRoman() throws Exception {

        // For each value in the range we support, convert to Roman numerals and back.
        for (int i = 1; i < 4000; i++) {
             String romanNum = romanNumberParser.getRomanNumFromInt(i);
             int numFromRoman = romanNumberParser.parseRomanNumToInt(romanNum);
            // The value should be the same!
            assertEquals(i, numFromRoman);
        }
    }

}