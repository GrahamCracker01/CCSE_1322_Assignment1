//Class: CSE 1322L
//Section: J03
//Term: Spring 2023
//Name: Asher Graham

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
class Poker {

    //declare objects and variables
    PlayingCards deck;
    ArrayList<String> hand1 = new ArrayList<>();
    ArrayList<String> hand2 = new ArrayList<>();

    public void dealhands() {
        hand1.add(0, deck.getNextCard());
        hand2.add(0, deck.getNextCard());
        hand1.add(1, deck.getNextCard());
        hand2.add(1, deck.getNextCard());
        hand1.add(2, deck.getNextCard());
        hand2.add(2, deck.getNextCard());
        hand1.add(3, deck.getNextCard());
        hand2.add(3, deck.getNextCard());
        hand1.add(4, deck.getNextCard());
        hand2.add(4, deck.getNextCard());
    }

    //default constructor
    public Poker() {
        deck = new PlayingCards();
        deck.Shuffle();
        dealhands();
    }

    //overloaded constructor
    public Poker(ArrayList<String> firstHand, ArrayList<String> secondHand) {
        deck = new PlayingCards();
        deck.Shuffle();
        hand1 = firstHand;
        hand2 = secondHand;
    }

    //show player hand
    public void showHand(int hand) {
        if (hand == 1) {
            System.out.println("Player 1's hand: \n" + hand1);
        }
        else {
            System.out.println("Player 2's hand: \n" + hand2);
        }
    }

    //count suites in hand
    public int[] countSuite(ArrayList<String> hand) {
        int club = 0, diamond = 0, heart = 0, spade = 0;
        for(String card : hand) {
            String[] suite = card.split(" ");
            if (suite[2].equals("Clubs")) {
                club++;
            }
            if (suite[2].equals("Diamonds")) {
                diamond++;
            }
            if (suite[2].equals("Hearts")) {
                heart++;
            }
            if (suite[2].equals("Spades")) {
                spade++;
            }

        }
        int[] output = new int[4];
        output[0] = club;
        output[1] = diamond;
        output[2] = heart;
        output[3] = spade;
        return output;
    }

    public int[] countValues(ArrayList<String> hand) {
        int[] output = new int[14];

        for (String card : hand) {
            String[] face = card.split(" ", 2);
            switch (face[0]) {
                case "A" -> output[1]++;
                case "2" -> output[2]++;
                case "3" -> output[3]++;
                case "4" -> output[4]++;
                case "5" -> output[5]++;
                case "6" -> output[6]++;
                case "7" -> output[7]++;
                case "8" -> output[8]++;
                case "9" -> output[9]++;
                case "10" -> output[10]++;
                case "J" -> output[11]++;
                case "Q" -> output[12]++;
                case "K" -> output[13]++;
            }
        }
        return output;
    }

    public int numPairs(int[] values) {
        int intPairs = 0;
        for (int card : values) {
            if (card == 2) {
                intPairs++;
            }
        }
        return intPairs;
    }

    public int threeOfAKind(int[] values) {
        int intCellOfThree = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == 3) {
                intCellOfThree = i;
            }
        }
        return intCellOfThree;
    }

    public int fourOfAKind(int[] values) {
        int intCellOfFour = 0, intLength = values.length;
        for (int i = 0; i < intLength; i++) {
            if (values[i] == 4) {
                intCellOfFour = i;
            }
        }
        return intCellOfFour;
    }

    public boolean fullHouse(int[] values) {
        return (numPairs(values) == 1 && threeOfAKind(values) > 0);
    }

    public boolean straight(int[] values) {
        boolean boolStraight = false;
        if (values[1] == 1 && values[2] == 1 && values[3] == 1 && values[4] == 1 && values[5] == 1) {
            boolStraight = true;
        } else if (values[2] == 1 && values[3] == 1 && values[4] == 1 && values[5] == 1 && values[6] == 1) {
            boolStraight = true;
        } else if (values[3] == 1 && values[4] == 1 && values[5] == 1 && values[6] == 1 && values[7] == 1) {
            boolStraight = true;
        } else if (values[4] == 1 && values[5] == 1 && values[6] == 1 && values[7] == 1 && values[8] == 1) {
            boolStraight = true;
        } else if (values[5] == 1 && values[6] == 1 && values[7] == 1 && values[8] == 1 && values[9] == 1) {
            boolStraight = true;
        } else if (values[6] == 1 && values[7] == 1 && values[8] == 1 && values[9] == 1 && values[10] == 1) {
            boolStraight = true;
        } else if (values[7] == 1 && values[8] == 1 && values[9] == 1 && values[10] == 1 && values[11] == 1) {
            boolStraight = true;
        } else if (values[8] == 1 && values[9] == 1 && values[10] == 1 && values[11] == 1 && values[12] == 1) {
            boolStraight = true;
        } else if (values[9] == 1 && values[10] == 1 && values[11] == 1 && values[12] == 1 && values[13] == 1) {
            boolStraight = true;
        } else if (values[10] == 1 && values[11] == 1 && values[12] == 1 && values[13] == 1 && values[1] == 1) {
            boolStraight = true;
        }
        return boolStraight;
    }

    public boolean flush(int[] suites) {
        return (suites[0] == 5 || suites[1] == 5 || suites[2] == 5 || suites[3] == 5);
    }

    public boolean straightFlush(int[] values, int[] suites) {
        return (straight(values) && flush(suites));
    }

    public boolean royalFlush(int[] values, int[] suites) {
        return ((suites[0]==5 || suites[1] == 5 || suites[2] == 5 || suites[3] == 5) && (values[1] == 1 && values[10] == 1 && values[11] == 1 && values[12] == 1 && values[13] == 1));
    }

    public String scoreHand(int input) {
        if (input == 1) {
            if (royalFlush(countValues(hand1), countSuite(hand1))) {
                return "Royal Flush";
            } else if (straightFlush((countValues(hand1)), countSuite(hand1))) {
                return "Straight Flush";
            } else if (fourOfAKind(countValues(hand1)) > 0) {
                return "Four of a Kind";
            } else if (fullHouse(countValues(hand1))) {
                return "Full House";
            } else if (flush(countSuite(hand1))) {
                return "Flush";
            } else if (straight(countValues(hand1))) {
                return "Straight";
            } else if (threeOfAKind(countValues(hand1)) > 0) {
                return "3 of a Kind";
            } else if (numPairs(countValues(hand1)) == 2) {
                return "2 Pairs";
            } else if (numPairs(countValues(hand1)) == 1) {
                return "1 Pair";
            } else {
                return "High Card";
            }
        } else{
            if (royalFlush(countValues(hand2), countSuite(hand2))) {
                return "Royal Flush";
            } else if (straightFlush((countValues(hand2)), countSuite(hand2))) {
                return "Straight Flush";
            } else if (fourOfAKind(countValues(hand2)) > 0) {
                return "Four of a Kind";
            } else if (fullHouse(countValues(hand2))) {
                return "Full House";
            } else if (flush(countSuite(hand2))) {
                return "Flush";
            } else if (straight(countValues(hand2))) {
                return "Straight";
            } else if (threeOfAKind(countValues(hand2)) > 0) {
                return "3 of a Kind";
            } else if (numPairs(countValues(hand2)) == 2) {
                return "2 Pairs";
            } else if (numPairs(countValues(hand2)) == 1) {
                return "1 Pair";
            } else {
                return "High Card";
            }
        }
    }
}