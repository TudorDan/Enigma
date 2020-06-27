package com.codecool.enigma;

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
            } else  if (argsParser.option.equals("-h")) {
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
            Cipher cipher = CipherFactory.getCipherForArgs(argsParser);

        } else {
            throw new EnigmaException("Invalid cipher");
        }
    }

}
