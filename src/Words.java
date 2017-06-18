import java.io.*;
import java.util.*;
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
    private final ArrayList<Character> letters;
    private final ArrayList<String> dict;
    private final String alpha = "aaabcdeeefghiiijklmnooopqrstuuuvwxyz";
    private boolean inPool;
    private boolean inDict;
    private boolean giveup;
    private int rounds;
    private int score;


    public Words() throws FileNotFoundException{
        letters = new ArrayList<>();

        for(int i=0; i<50; i++){
            Random randNum = new Random();
            int stringIndex = randNum.nextInt(35);
            letters.add(alpha.charAt(stringIndex));
        }

        dict = new ArrayList<>();
        Scanner fileRead = new Scanner(new File("dictionary.txt"));
        while(fileRead.hasNextLine()){
            dict.add(fileRead.nextLine());
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

    //public boolean getInPool(){return inPool;}

    public void setInPool(boolean bool){inPool = bool;}

    public boolean getInDict(){return inDict;}

    public void setInDict(boolean bool){inDict = bool;}


    public boolean inDict(String userInput){
        boolean result;
        String toLowerCase = userInput.toLowerCase();
        if(dict.contains(toLowerCase) == false)
        {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    public boolean inPool(String userInput){
        //Check that letters are in the letterPool
        for(int i=0; i<userInput.length(); i++){
            for(int j=0; j<letters.size(); j++){
                if(userInput.charAt(i) != (Character.toLowerCase(letters.get(j)))){
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
            //System.out.println(Character.toUpperCase((Character)userInput.charAt(j)));
            // char letter = (Character)userInput.charAt(j);
            // char upperLetter = Character.toUpperCase(letter);
            // System.out.println(letter + " " + upperLetter);
            letters.remove((Character)userInput.charAt(j));
            score++;
        }
    }

    public boolean isEmpty(){return letters.isEmpty();}

    public ArrayList<Character> printOut(){
        return letters;
    }
}
