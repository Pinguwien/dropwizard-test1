package com.dwis.calc.service;

/**
 * Created by Dominik on 03.04.16.
 */
public class CalculatorServiceImpl implements CalculatorService {

    //TODO: DI
    private RomanNumbers numberParser = new RomanNumbers();

    @Override
    public String calculate(String leftNum, String operator, String rightNum) {

        String result;
        int leftNumber = numberParser.parseRomanNumToInt(leftNum);

        if (leftNumber == -1) {
            result = "error! First Roman Number could not be parsed";
        }

        int rightNumber = numberParser.parseRomanNumToInt(rightNum);
        if (rightNumber == -1) {
            result = "error! Second Roman Number could not be parsed";
        }

        int calcResult = 0;

        switch (operator) {
            case "+":
                calcResult = leftNumber + rightNumber;
                break;
            case "-":
                calcResult = leftNumber - rightNumber;

                break;
            case "*":
                calcResult = leftNumber * rightNumber;

                break;
            case "/":
                calcResult = leftNumber / rightNumber;
                break;
            case "%":
                calcResult = leftNumber % rightNumber;
                break;
            default:
                result = "unknown Operator! Please use +, -, *, /or % ";
                break;
        }

        if (calcResult < 1)
            result = "result too small";
        else if (calcResult >= 4000)
            result = "result too large";
        else {
            result = numberParser.getRomanNumFromInt(calcResult);
        }
        return result;
    }
}
