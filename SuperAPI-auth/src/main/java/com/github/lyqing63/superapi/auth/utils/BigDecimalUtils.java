package com.github.lyqing63.superapi.auth.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

  public static BigDecimal doubleAdd(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.add(b2);
     }

      public static BigDecimal floatAdd(float v1, float v2) {
            BigDecimal b1 = new BigDecimal(Float.toString(v1));
            BigDecimal b2 = new BigDecimal(Float.toString(v2));
            return b1.add(b2);
     }

      public static BigDecimal doubleSubtract(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.subtract(b2);
     }

      public static BigDecimal floatSubtract(float v1, float v2) {
            BigDecimal b1 = new BigDecimal(Float.toString(v1));
            BigDecimal b2 = new BigDecimal(Float.toString(v2));
            return b1.subtract(b2);
     }

      public static BigDecimal doubleMultiply(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.multiply(b2);
     }

      public static BigDecimal floatMultiply(float v1, float v2) {
            BigDecimal b1 = new BigDecimal(Float.toString(v1));
            BigDecimal b2 = new BigDecimal(Float.toString(v2));
            return b1.multiply(b2);
     }

      public static BigDecimal doubleDivide(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));

            return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
     }

      public static BigDecimal floatDivide(float v1, float v2) {
            BigDecimal b1 = new BigDecimal(Float.toString(v1));
            BigDecimal b2 = new BigDecimal(Float.toString(v2));

            return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
     }

      public static int doubleCompareTo(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return   b1.compareTo(b2);
     }

      public static int floatCompareTo(float v1, float v2) {
            BigDecimal b1 = new BigDecimal(Float.toString(v1));
            BigDecimal b2 = new BigDecimal(Float.toString(v2));
            return   b1.compareTo(b2);
     }

}
