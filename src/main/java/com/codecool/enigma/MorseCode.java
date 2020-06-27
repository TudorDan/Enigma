package com.codecool.enigma;

class MorseCode implements Cipher {

    String[] english = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", " "};

    String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
            "-----", "/"};

    @Override
    public String encrypt(String message) {
        int messLength = message.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < messLength; i++) {
            boolean found = false;
            String temp = "";
            if (Character.isLetter(message.charAt(i))) {
                temp += Character.toUpperCase(message.charAt(i));
            } else {
                temp += message.charAt(i);
            }
            for (int j = 0; j < 37 && !found; j++) {
                if (english[j].equals(temp)) {
                    result.append(morse[j]);
                    found = true;
                }
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String message) {
        int messLength = message.length();
        StringBuilder result = new StringBuilder();
        String temp = "";
        for (int i = 0; i < messLength; i++) {
            if (message.charAt(i) == ' ' || (i == messLength -1 && !temp.isEmpty())) {
                boolean found = false;
                for (int j = 0; j < 37 && !found; j++) {
                    if (morse[j].equals(temp)) {
                        result.append(english[j]);
                        found = true;
                    }
                }
                temp = "";
            } else {
                temp += message.charAt(i);
            }
        }
        return result.toString();
    }
}
