package com.codecool.enigma;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Enigma {

    private static String MENU = "Enigma Manual\n" +
            "Run options: [-h | -e | -d] {CipherName} {FileName} {EncryptionKey}\n" +
            "   -h : displays this menu; other arguments are ignored.\n" +
            "   -e : encrypt and display\n" +
            "   -d : decrypt and display\n" +
            "   CipherName      : cipher to use when encrypting/decrypting; [rot13, rail-fence, morse]\n" +
            "   FileName        : path to file to encrypt/decrypt\n" +
            "   EncryptionKey   : Optional -> must be provided if cipher requires a key";

    public static void main(String[] args) {
        try {
            ArgsParser argsParser = new ArgsParser(args);
            if (argsParser.option == null) {
                throw new EnigmaException("Missing option -e or -d or -h");
            } else if (argsParser.option.equals("-h")) {
                System.out.println(MENU);
            } else if (argsParser.option.equals("-e") || argsParser.option.equals("-d")) {
                handleCipherOperation(argsParser);
            } else {
                throw new EnigmaException("Invalid option");
            }
        } catch (EnigmaException e) {
            System.out.println("Wrong input: " + e.getMessage());
            System.out.println(MENU);
        }
    }

    private static void handleCipherOperation(ArgsParser argsParser) throws EnigmaException {
        if (CipherFactory.isCipherAvailable(argsParser.cipher)) {
            // build the cipher
            Cipher cipher = CipherFactory.getCipherForArgs(argsParser);

            // read file content
            StringBuilder fileContent = new StringBuilder();
            if (argsParser.file == null) {
                throw new EnigmaException("Missing file path!");
            }

            //get file path
            Path filePath;
            try {
                filePath = Paths.get(argsParser.file).toRealPath();
            } catch (Exception x) {
                throw new EnigmaException("Missing or unreadable file: " + x.getMessage());
            }

            //read file content
            try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
                fileContent.deleteCharAt(fileContent.length() - 1); //delete last new line
            } catch (Exception x) {
                throw new EnigmaException("Unable to read file: " + x.getMessage());
            }

            //cipher!
            assert cipher != null;
            if (argsParser.option.equals("-e")) {
                System.out.println(cipher.encrypt(fileContent.toString()));
            } else {
                System.out.println(cipher.decrypt(fileContent.toString()));
            }
        } else {
            throw new EnigmaException("Invalid cipher");
        }
    }

}
