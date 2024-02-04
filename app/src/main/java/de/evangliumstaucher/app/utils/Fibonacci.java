package de.evangliumstaucher.app.utils;

public class Fibonacci {
    public static int nthFibonacciTerm(int n) {
        double squareRootOf5 = Math.sqrt(5);
        double phi = (1 + squareRootOf5)/2;
        int nthTerm = (int) ((Math.pow(phi, n) - Math.pow(-phi, -n))/squareRootOf5);
        return nthTerm;
    }
}
