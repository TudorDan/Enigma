package com.codecool.enigma;

class EnigmaException extends Exception {
    private static final long serialVersionUID = 65465465L;

    public EnigmaException(String message) {
        super(message);
    }

    public EnigmaException(String message, Throwable cause) {
        super(message, cause);
    }
}
