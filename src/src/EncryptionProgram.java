import java.util.*;

public class EncryptionProgram {
    private Scanner scanner;
    private Random random;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character; // this is going to be the starting position of the characters I am using.
    private String line;
    private char[] letters;
    EncryptionProgram(){
        scanner = new Scanner(System.in);
        random = new Random();
        list = new ArrayList<>();
        shuffledList = new ArrayList<>();
        character = ' ';
        newKey();
        askQuestion();
    }
    private void askQuestion(){
        while(true) {
            System.out.println("********************************************");
            System.out.println("What do you want to do?");
            System.out.println("(N)ewKey,(G)etKey,(E)ncrypt,(D)ecrypt,(Q)uit");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0)); // takes the first letter and turns it to upper case and store it with the response variable.
            switch (response) { // looks for the matches of the first letter.
                case 'N' -> newKey();
                case 'G' -> getKey();
                case 'E' -> encrypt();
                case 'D' -> decrypt();
                case 'Q' -> quit();
                default -> System.out.println("Not a valid answer.");
            }
        }
    }
    private void newKey(){
        character = ' ';
        list.clear();
        shuffledList.clear();
        for(int i = 32; i < 127; i++) { // we start at 32 because on the ASCII table space(' ') is 32 on the table. I am using the range 32 - 127 because these happen to be the ASCII keys I want to isolate and use to encrypt the messages.
            list.add(character); // we take the list and add the character value of the character and increment by 1 after each iteration of the for loop.
            character++;
        }
        shuffledList = new ArrayList<>(list);
        Collections.shuffle(shuffledList); // this shuffles the shuffled list.
        System.out.println("A new key has been generated.");
    }
    private void getKey(){
        System.out.println("Key: ");
        for(Character x : list) { // this will print the list and print of the ASCII table.
            System.out.print(x);
        }
        System.out.println();
        for(Character x : shuffledList) { // I took the list and shuffled the list for the encryption.
            System.out.print(x);
        }
        System.out.println();
    }
    private void encrypt(){ // this will encrypt plain text as cipher text.
        System.out.println("Enter a message to be encrypted: ");
        String message = scanner.nextLine();
        letters = message.toCharArray();
        for(int i = 0; i < letters.length; i++) { // this is used to iterate through the array of character letters.
            for(int j = 0; j < list.size(); j++) { // looks to see if there is a matching letter for the current letter in the array.
                if(letters[i] == list.get(j)) {
                    letters[i] = shuffledList.get(j);
                    break;
                }
            }
        }
        System.out.println("Encrypted: ");
        for(char x : letters) { // iterates through each letter of the array of characters
            System.out.print(x);
        }
        System.out.println(); // this is here to space out the text.
    }
    private void decrypt(){ // this takes cipher text and converts it to plain text. This is the exact same as the encrypted method but reversed.
        System.out.println("Enter a message to be decrypted: ");
        String message = scanner.nextLine();
        letters = message.toCharArray();
        for(int i = 0; i < letters.length; i++) {
            for(int j = 0; j < shuffledList.size(); j++) {
                if(letters[i] == shuffledList.get(j)) {
                    letters[i] = list.get(j);
                    break;
                }
            }
        }
        System.out.println("Decrypted: ");
        for(char x : letters) {
            System.out.print(x);
        }
        System.out.println();
    }
    private void quit(){
        System.out.println("Program closing.");
        System.exit(0);
    }
}

/*
I feel like all the methods are self-explanatory, but I will try and add comments next to the confusing parts.
everything is private and final to add some level of security here.
 */