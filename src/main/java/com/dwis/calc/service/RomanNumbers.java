package com.dwis.calc.service;

/**
 * Created by Dominik on 03.04.16.
 */
public class RomanNumbers {

    public String getRomanNumFromInt(int intVal){

        String result = "";

        if (intVal < 1 || intVal >= 4000) {
            return "error, integer Value out of valid Range for roman Number.";
        }

        while (intVal >= 1000) {
            result += "M";
            intVal -= 1000;
        }
        if (intVal >= 900) {
            result += "CM";
            intVal -= 900;
        }
        if (intVal >= 500) {
            result += "D";
            intVal -= 500;
        }
        if (intVal >= 400) {
            result += "CD";
            intVal -= 400;
        }
        while (intVal >= 100) {
            result += "C";
            intVal -= 100;
        }
        if (intVal >= 90) {
            result += "XC";
            intVal -= 90;
        }
        if (intVal >= 50) {
            result += "L";
            intVal -= 50;
        }
        if (intVal >= 40) {
            result += "XL";
            intVal -= 40;
        }
        while (intVal >= 10) {
            result += "X";
            intVal -= 10;
        }
        if (intVal >= 9) {
            result += "IX";
            intVal -= 9;
        }
        if (intVal >= 5) {
            result += "V";
            intVal -= 5;
        }
        if (intVal >= 4) {
            result += "IV";
            intVal -= 4;
        }
        while (intVal > 0) {
            result += "I";
            intVal -= 1;
        }

        return result;
    }

    public int parseRomanNumToInt(String romanNumber) {
        int resultInt = 0;
        int romanLength = romanNumber.length();

        if(romanLength > 0){
            for (int i = 0; i < romanLength; ) {

                char currentChar = romanNumber.charAt(i);
                char nextChar;

                if (i < romanLength - 1) {
                    nextChar = romanNumber.charAt(i + 1);
                }
                else {
                    nextChar = '?'; //dummy for last char, where next is not defined.
                }

                if (currentChar == 'M') {
                    resultInt += 1000;
                    i += 1;
                } else if (currentChar == 'C' && nextChar == 'M') {
                    resultInt += 900;
                    i += 2;
                } else if (currentChar == 'D') {
                    resultInt += 500;
                    i += 1;
                } else if (currentChar == 'C' && nextChar == 'D') {
                    resultInt += 400;
                    i += 2;
                } else if (currentChar == 'C') {
                    resultInt += 100;
                    i += 1;
                } else if (currentChar == 'X' && nextChar == 'C') {
                    resultInt += 90;
                    i += 2;
                } else if (currentChar == 'L') {
                    resultInt += 50;
                    i += 1;
                } else if (currentChar == 'X' && nextChar == 'L') {
                    resultInt += 40;
                    i += 2;
                } else if (currentChar == 'X') {
                    resultInt += 10;
                    i += 1;
                } else if (currentChar == 'I' && nextChar == 'X') {
                    resultInt += 9;
                    i += 2;
                } else if (currentChar == 'V') {
                    resultInt += 5;
                    i += 1;
                } else if (currentChar == 'I' && nextChar == 'V') {
                    resultInt += 4;
                    i += 2;
                } else if (currentChar == 'I') {
                    resultInt += 1;
                    i += 1;
                } else {
                    //error.
                    resultInt = -1;
                }
            }
        } else {
            resultInt = -1;
        }
        return resultInt;
    }
}
