package com.codecool.enigma;

class CipherFactory {

    static boolean isCipherAvailable(String cipherName) {
        return cipherName != null && (cipherName.equals("rot13") || cipherName.equals("rail-fence") || cipherName.equals(
                "morse"));
    }

    static Cipher getCipherForArgs(ArgsParser args) throws EnigmaException {
        switch (args.cipher) {
            case "rot13":
                return new Rot13();
            case "morse":
                return new MorseCode();
            case "rail-fence":
                if (args.key == null) {
                    throw new EnigmaException("Key is missing!");
                }
                try {
                    int key = Integer.parseInt(args.key);
                    return new RailFence(key);
                } catch (Exception e) {
                    throw new EnigmaException("Key must be an integer!");
                }
        }
        return null;
    }
}
