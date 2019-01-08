package com.app.service;

import com.app.exceptions.MyException;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MyData {
    private static Scanner sc = new Scanner(System.in);

    public static String getString(String message) {
        try {
            System.out.println(message);
            return sc.nextLine();
        } catch (Exception e) {
            throw new MyException("GET STRING EXCEPTION", LocalDateTime.now());
        }
    }

    public static int getInt(String message) {
        try {
            System.out.println(message);
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            throw new MyException("GET INT EXCEPTION", LocalDateTime.now());
        }
    }

    public static double getDouble(String message) {
        try {
            System.out.println(message);
            return Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            throw new MyException("GET DOUBLE EXCEPTION", LocalDateTime.now());
        }
    }

    public static void close() {
        if (sc != null) {
            sc.close();
        }
    }
}
