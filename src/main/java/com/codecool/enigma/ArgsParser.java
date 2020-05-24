package com.codecool.enigma;

class ArgsParser {

    String option = null;
    String cipher = null;
    String file = null;
    String key = null;

    ArgsParser(String[] args) {
        if (args.length >= 4) {
            key = args[3];
        }
        if (args.length >= 3) {
            file = args[2];
        }
        if (args.length >= 2) {
            cipher = args[1];
        }
        if (args.length >= 1) {
            option = args[0];
        }
    }
}
