package com.HowToLearnEnglishWords;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.Caret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

/**
 * Created by Akyrt on 2017-04-28.
 */
public class guiWordsLearn extends JFrame implements ActionListener {

    private JTextField randomScoreField;
    private JTextField inputWordTextField;
    private JTextField yourScoreTextField;
    private JLabel yourRandomWordLabel;
    private JLabel translatePleaseLabel;
    private JLabel scoreLabel;
    private JButton pathEngButton;
    private JButton pathPolButton;
    private JButton startLearningButton;
    private JButton resetLearningButton;
    private JLabel howManyWordsLeftLabel;
    private JTextField howManyWordsLeftTextField;
    Color colorScore = new Color(0, 128, 0);

    private actionsWordsLearn word;
    private int tmpPoints = 1;
    int sum = 0;
    //Create a file chooser
    final JFileChooser fc = new JFileChooser();


    public guiWordsLearn() throws FileNotFoundException {
        word = new actionsWordsLearn();
        //setResizable(true);
        setVisible(true);

checkSum();
        makeGUI();
        pack();


    }

    public void makeGUI() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        designGUI();
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Sets app title
        this.setTitle("WordsLearn");
       // this.getContentPane().setBackground(Color.WHITE);
        setLayout(new GridLayout(6, 1));

        Font font  = new Font("SansSerif", Font.BOLD, 17);
//      Set buttons for including a file paths
        pathEngButton = new JButton("1. First path");
        pathEngButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pathEngButton.setFont(font);
        pathEngButton.setToolTipText("Include path to first set of words file, please.");
        pathEngButton.addActionListener(this);
        add(pathEngButton);

        pathPolButton = new JButton("2. Second path");
        pathPolButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pathPolButton.setFont(font);
        pathPolButton.setToolTipText("Include path to second set of words file, please.");
        pathPolButton.addActionListener(this);
        add(pathPolButton);

//        A button for start learning
        startLearningButton = new JButton("Start learning");
        startLearningButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        startLearningButton.setFont(font);
        startLearningButton.addActionListener(this);
        add(startLearningButton);

        resetLearningButton = new JButton("Reset learning");
        resetLearningButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        resetLearningButton.setFont(font);
        resetLearningButton.addActionListener(this);
        add(resetLearningButton);

        yourRandomWordLabel = new JLabel("Your random word: ",SwingConstants.CENTER);
       // yourRandomWordLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        yourRandomWordLabel.setFont(font);
        add(yourRandomWordLabel);

        translatePleaseLabel = new JLabel("Translate please: ",SwingConstants.CENTER);
       // translatePleaseLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        translatePleaseLabel.setFont(font);
        add(translatePleaseLabel);

//        Text field for show a result of a words rand from a text file
        randomScoreField = new JTextField();
        randomScoreField.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        Color c = new Color(159, 251, 136);
        randomScoreField.setBackground(c);
        randomScoreField.setHorizontalAlignment(SwingConstants.CENTER);
        randomScoreField.setText(" ");
        randomScoreField.setFont(font);
        randomScoreField.setEditable(false);
        add(randomScoreField);

//        Text field for input ansfer by a user
        inputWordTextField = new JTextField();
        inputWordTextField.setToolTipText("Type your translation, then press ENTER. ");
        inputWordTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        inputWordTextField.setHorizontalAlignment(SwingConstants.CENTER);
        inputWordTextField.setFont(font);
        inputWordTextField.setBackground(Color.ORANGE);
        inputWordTextField.setForeground(Color.BLACK);
        inputWordTextField.addActionListener(this);
        add(inputWordTextField);

//        Score field

        scoreLabel = new JLabel("Score: ");
       // scoreLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

      //  scoreLabel.setForeground(colorScore);
        scoreLabel.setFont(font);
        add(scoreLabel);

        yourScoreTextField = new JTextField();
       // yourScoreTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        yourScoreTextField.setFont(font);
        yourScoreTextField.setEditable(false);
        add(yourScoreTextField);

        howManyWordsLeftLabel = new JLabel("This word will repeated: ");
       // howManyWordsLeftLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        howManyWordsLeftLabel.setFont(font);
        add(howManyWordsLeftLabel);

        howManyWordsLeftTextField = new JTextField();
       // howManyWordsLeftTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        howManyWordsLeftTextField.setHorizontalAlignment(SwingConstants.CENTER);
        howManyWordsLeftTextField.setFont(font);
        howManyWordsLeftTextField.setEditable(false);
        add(howManyWordsLeftTextField);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int o = JOptionPane.showConfirmDialog(
                        e.getWindow(),
                        "Chcesz zakoczńczyć?",
                        "Finish Application Dialog",
                        JOptionPane.YES_NO_OPTION
                );
                if (o == JOptionPane.YES_OPTION) {


                    dispose();

                } else if (o == JOptionPane.NO_OPTION) {
                    scoreLabel.setText("Score: " + word.getPoints() + "/" + word.getAllTests());
                }
                ;

            }
        });


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inputWordTextField) {
            word.allTests++;
            word.setWord(inputWordTextField.getText());
            word.compareWords(word.ourWord);
            // yourScoreTextField.setText(word.getPoints() + "/" + word.getAllTests() + ", to be translated: " + sum);

            checkSum();
            yourScoreTextField.setText(" To be translated: " + sum);

            if ((sum > 0) && (word.rememberWordApperance() == 1)) {

                word.points++;
                checkSum();
                // yourScoreTextField.setText(word.getPoints() + "/" + word.getAllTests() + ", to be translated: " + sum);
                yourScoreTextField.setText(" To be translated: " + sum);

                setPointsIcon();
                doRandom();
                showDrawnWords();
                howManyWordsLeftTextField.setText(word.rememberWordRepetition.get(word.k) + " more times");
                howManyWordsLeftTextField.setHorizontalAlignment(SwingConstants.CENTER);

            } else if (sum > 0 && (word.rememberWordApperance() == 0)) {
                doRandom();
                showDrawnWords();
                setPointsIcon();
                howManyWordsLeftTextField.setText(word.rememberWordRepetition.get(word.k) + " ");
                howManyWordsLeftTextField.setHorizontalAlignment(SwingConstants.CENTER);



            } else if (sum == 0 /*&& (word.rememberWordApperance() == 0)*/) {
                try {
                    resetApp();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

            }

            inputWordTextField.setText("");

        } //*************************************************************

        if (e.getSource() == pathEngButton) {

            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String file = fc.getSelectedFile().getAbsolutePath();
                //This is where a real application would open the file.
                word.setPathEng(file);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            } else {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }

        }

        if (e.getSource() == pathPolButton) {

            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String file = fc.getSelectedFile().getAbsolutePath();
                word.setPathPol(file);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            } else {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }
        }

        if (e.getSource() == startLearningButton) {
            try {
                word.readWordsFile();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            word.randomWord();
            showDrawnWords();
            checkSum();
            howManyWordsLeftTextField.setText(word.rememberWordRepetition.get(word.k) + " ");
            //yourScoreTextField.setText(" To be translated: ");
            yourScoreTextField.setText(" To be translated: " + sum);


        }

        if (e.getSource() == resetLearningButton) {

            try {
                resetApp();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        }

    }

    public void resetApp() throws FileNotFoundException {

        tmpPoints = 1;
        word.k = 0;
        word.allTests = 0;
        word.points = 0;
        word.wynik = false;

        word.readWordsFile();
        word.randomWord();
        showDrawnWords();
        checkSum();
        yourScoreTextField.setText(" To be translated: " + sum);

        scoreLabel.setIcon(new ImageIcon(" "));
        scoreLabel.setForeground(Color.BLACK);
        howManyWordsLeftTextField.setText(word.rememberWordRepetition.get(word.k) + " ");
        scoreLabel.setText("Score: " + word.getPoints() + "/" + word.getAllTests());

    }

    public void checkSum() {
        sum = 0;
     //   sum = 1;
        for (int i : word.rememberWordRepetition) {

            sum += i;

        }
    }

    public void setPointsIcon() {
        if (word.points == tmpPoints) {
           // scoreLabel.setForeground(Color.GREEN);
            scoreLabel.setForeground(colorScore);
            scoreLabel.setText("Score: " + word.getPoints() + "/" + word.getAllTests());
            tmpPoints = word.points;
            tmpPoints++;

        } else if (word.points < tmpPoints) {
            scoreLabel.setForeground(Color.RED);
            scoreLabel.setText("Score: " + word.getPoints() + "/" + word.getAllTests());

            tmpPoints = word.points;
            tmpPoints++;
        }
    }


    void showDrawnWords() {
        if (word.r == 0) {
            randomScoreField.setText(word.listaEng.get(word.k));
        } else randomScoreField.setText(word.listaPol.get(word.k));
    }

    public void doRandom() {
        do {
            word.randomWord();
            //showPoints();
            checkSum();
            if (word.rememberWordRepetition.get(word.k)!= 0) break;
            if(sum==0) break;
        } while (word.rememberWordRepetition.get(word.k) == 0);

    }

    void designGUI() {

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenuItem helpItem = new JMenuItem("Help");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> {

            dispose();
            System.exit(0);
        });

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> {
            //1. Create the frame.
            JFrame frame = new JFrame("About");

//2. Optional: What happens when the frame closes?
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//3. Create components and put them in the frame.

            JTextArea labell = new JTextArea();

            labell.setText("\n Aplikacja do nauki słówek z języków obcych. \n\n Autor: Akyrt \n Data wersji:  11.06.2017 \n");
           // labell.setFont(font);
            labell.setEditable(false);

            frame.getContentPane().add(labell, BorderLayout.CENTER);

            frame.setResizable(false);
//4. Size the frame.
            frame.pack();

//5. Show it.
            frame.setVisible(true);
        });



        helpItem.addActionListener(e -> {
            //1. Create the frame.
            JFrame frame = new JFrame("Help");

//2. Optional: What happens when the frame closes?
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//3. Create components and put them in the frame.

            JTextArea labell = new JTextArea();

            labell.setText("\nWitam, zanim zaczniesz korzystać z mojego programu przeczytaj proszę ten wstęp. " +
                    "\n\n 1.Program służy do nauki słówek z angielskiego, działa na zasadzie losowania słówek z 2 plików .txt, " +
                    "\n   w których są umieszczone słówka. W jednym słowka po angielsku w drugim po polsku. " +
                    "\n\n 2.Aby uruchomić program, przy pierwszym użyciu należy  klikając PPM-> otwórz za pomocą -> Java Platform. " +
                    "\n\n 3.Dołączone 2 pliki tekstowe zawierają przykładowe słówka, jak należy wpisać je do pliku. " +
                    "\n   Można do nich dopisać swoje i rozpocząć naukę." +
                    "\n\n 4.Swoje słówka należy wpisywać w plikach kolejno jedno pod drugim oraz WAŻNE aby ich tłumaczenia w drugim" +
                    "\n   pliku były w tej samej kolejności !" +
                    "\n\n 5.Równie dobrze można ćwiczyć słówka z różnych innych języków po wcześniejszym rozpisaniu ich w plikach .txt." +
                    "\n\n 6.PO KAŻDYM uruchomieniu aplikacji, należy wstępnie załadować obydwa pliki tekstowe a następnie kliknąć Start." +
                    "\n\n 7.Tłumaczenie wpisujemy do okienka \"Translate please\" a następnie klikamy ENTER." +
                    "\n\n 8.Miłej nauki ! :)");
            // labell.setFont(font);
            labell.setEditable(false);

            frame.getContentPane().add(labell, BorderLayout.CENTER);

            frame.setResizable(false);
//4. Size the frame.
            frame.pack();

//5. Show it.
            frame.setVisible(true);
        });

        fileMenu.add(helpItem);
        fileMenu.add(aboutItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        setLayout(new BorderLayout());
    }

}