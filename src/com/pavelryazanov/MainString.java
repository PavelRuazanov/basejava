package com.pavelryazanov;

public class MainString {
    public static void main(String[] args) {
        String[] strings = new String[]{"1", "2", "3", "4", "5"};
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strings) {
            stringBuilder.append(str).append(", ");
        }
        System.out.println(stringBuilder);
    }
}
