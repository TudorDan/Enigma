package com.codecool.enigma;

class Rot13 implements Cipher {

    String decrypted = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String encrypted = "nopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklm";

    @Override
    public String encrypt(String message) {
        int messLength = message.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < messLength; i++) {
            if (Character.isLetter(message.charAt(i))) {
                result.append(encryptLetter(message.charAt(i)));
            } else {
                result.append(message.charAt(i));
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String message) {
        int messLength = message.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < messLength; i++) {
            if (Character.isLetter(message.charAt(i))) {
                result.append(decryptLetter(message.charAt(i)));
            } else {
                result.append(message.charAt(i));
            }
        }
        return result.toString();
    }

    private char encryptLetter(char letter) {
        boolean found = false;
        for (int i = 0; i < 52 && !found; i++) {
            if (decrypted.charAt(i) == letter) {
                letter = encrypted.charAt(i);
                found = true;
            }
        }
        return letter;
    }

    private char decryptLetter(char letter) {
        boolean found = false;
        for (int i = 0; i < 52 && !found; i++) {
            if (encrypted.charAt(i) == letter) {
                letter = decrypted.charAt(i);
                found = true;
            }
        }
        return letter;
    }

}
