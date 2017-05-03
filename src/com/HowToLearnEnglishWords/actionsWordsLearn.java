package com.HowToLearnEnglishWords;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Akyrt on 2017-04-28.
 */
public class actionsWordsLearn {

    public List<String> listaEng, listaPol;
    List<Integer> rememberWordRepetition;// = new ArrayList<>();
    String randScore;
    Random someWord;
    public int k;
    public int r;
    int points;
    int allTests;
    boolean wynik;
    String ourWord;
    String pathEng;
    String pathPol;

    /**
     * A constructor for app
     */
    actionsWordsLearn() throws FileNotFoundException {
        listaEng = new ArrayList<>();
        listaPol = new ArrayList<>();
        rememberWordRepetition = new ArrayList<>();
        k = 0;
        r =0;
        allTests = 0;
        points = 0;
        wynik = false;
    }


    /**
     * Sets the path to file
     */

    public void setPathEng(String pathEng) {
        this.pathEng = pathEng;
    }

    public void setPathPol(String pathPol) {
        this.pathPol = pathPol;
    }

    public int getPoints() {
        return points;
    }

    public int getAllTests() {
        return allTests;
    }

    public void setWord(String w) {
        ourWord = w;
    }

    /**
     * Reading a text from selected files.
     */
    public void readWordsFile() throws FileNotFoundException {
        String fileEngWords = pathEng;
        String filePolWords = pathPol;

        File textEngWordFile = new File(fileEngWords);
        File textPolWordFile = new File(filePolWords);
        Scanner inEng = new Scanner(textEngWordFile);
        Scanner inPol = new Scanner(textPolWordFile);
        rememberWordRepetition.clear();
        listaEng.clear();
        listaPol.clear();



        while (inEng.hasNextLine()) {

            listaEng.add(inEng.nextLine());
            rememberWordRepetition.add(3);
        }
        while (inPol.hasNextLine()) {

            listaPol.add(inPol.nextLine());
        }

        inPol.close();
        inEng.close();
    }

    /**
     * Draws a one word from included files.
     */
    public void randomWord() {

        randScore = new String();
        someWord = new Random();

        k = someWord.nextInt(listaPol.size());
        r = someWord.nextInt(2);
        if (r == 0) {

                randScore = listaPol.get(k);
//            System.out.println(randScore);

        } else {

                randScore = listaEng.get(k);
//            System.out.println(randScore);

        }

    }

    public int rememberWordApperance(){
        int tmp;
        if ((wynik == true) && (rememberWordRepetition.get(k) <= 3) && (rememberWordRepetition.get(k) >= 1)) {

            tmp = rememberWordRepetition.get(k);
           if (tmp>=1){ tmp--;}
            rememberWordRepetition.set(k, tmp);
            return 1;
        }else return 0;
    }

    /**
     * Compare a drawed word with a writed word by user.
     */

    public void compareWords(String inputWord) {
       // allTests++;
        if (randScore.equals(inputWord)) {

                wynik = true;
               // points++;

        } else {
            wynik = false;
        }


    }


}
