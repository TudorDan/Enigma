package com.codecool.enigma;

import java.util.HashMap;

class RailFence implements Cipher {
    private int key;

    public RailFence(int key) {
        this.key = key;
    }

    @Override
    public String encrypt(String message) {
        //define and initialize encrypting lines
        StringBuilder[] temp = new StringBuilder[key];
        for (int i = 0; i < key; i++) {
            temp[i] = new StringBuilder();
        }

        //encrypt
        int i = 0;
        while (i < message.length()) {
            //zig
            int line = 0;
            while (i < message.length() && line < key - 1) {
                temp[line++].append(message.charAt(i++));
            }

            //zag
            line = key - 1;
            while (i < message.length() && line > 0) {
                temp[line--].append(message.charAt(i++));
            }
        }

        //construct encrypted message
        StringBuilder result = new StringBuilder();
        for (int line = 0; line < key; line++) {
            result.append(temp[line].toString());
        }

        return result.toString();
    }

    @Override
    public String decrypt(String message) {
        StringBuilder result = new StringBuilder(message);

        int encIndex = 0;
        boolean descending = true;
        for (int line = 0; line < key; line++) {
            int lineIndex = 0;
            for (int decIndex = 0; decIndex < message.length(); decIndex++) {
                if (lineIndex == line) {
                    result.setCharAt(decIndex, message.charAt(encIndex++));
                }

                if (descending) {
                    lineIndex++;
                } else {
                    lineIndex--;
                }
                if (lineIndex == key - 1) {
                    descending = false;
                }
                if (lineIndex == 0) {
                    descending = true;
                }
            }
        }

        return result.toString();
    }
}
