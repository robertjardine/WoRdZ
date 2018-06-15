import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Robert Jardine
 * An ArrayList will be populated with random letters.
 * When a user enters a word, it will check that it is
 * a valid English word and that all words are present
 * in the pool of letters. It will then delete those
 * characters from the ArrayList and move to the next
 * round. One point is awarded per character.
 *
 */

public class Words {

    private static final String ALPHA = "aaabcdeeefghiiijklmnooopqrstuuuvwxyz";

    private boolean inPool;
    private boolean inDict;
    private boolean giveup;
    private int rounds;
    private int score;
    private List<Character> letters;
    private List<String> dict;


    public Words() throws FileNotFoundException {
        letters = new ArrayList<>();

        for(int i=0; i<50; i++){
            Random randNum = new Random();
            int stringIndex = randNum.nextInt(35);
            letters.add(ALPHA.charAt(stringIndex));
        }

        dict = new ArrayList<>();
        try (Scanner fileRead = new Scanner(new File("dictionary.txt"))) {
            while (fileRead.hasNextLine()) {
                dict.add(fileRead.nextLine());
            }
        }

        giveup = false;

        inPool = false;
    }

    public boolean getGiveup(){return giveup;}

    public void setGiveup(boolean bool){giveup = bool;}

    public int poolSize(){return letters.size();}

    public int getRounds(){return rounds;}

    public void addRound(){rounds++;}

    public int getScore(){return score;}

    public void setScore(int addScore){score += addScore;}

    public void setInPool(boolean bool){inPool = bool;}

    public boolean getInDict(){return inDict;}

    public void setInDict(boolean bool){inDict = bool;}


    public boolean inDict(String userInput){
       return  dict.contains(userInput.toLowerCase());
    }

    public boolean inPool(String userInput){
        for(int i=0; i<userInput.length(); i++){
            for(char letter : letters){
                if(userInput.charAt(i) != (Character.toLowerCase(letter))){
                    inPool = false;
                } else {
                    inPool = true;
                    break;
                }
            }
            if(!inPool){
                break;
            }
        }
        return inPool;
    }

    public void deleteLetters(String userInput){
        for(int j=0; j<userInput.length(); j++){
            letters.remove((Character)userInput.charAt(j));
            score++;
        }
    }

    public boolean isEmpty(){return letters.isEmpty();}

    public List<Character> printOut(){
        return letters;
    }
}
