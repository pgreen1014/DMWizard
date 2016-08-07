package com.philipgreen.dmwizard.dice;

/**
 * Created by pgreen on 8/6/16.
 */
public class Dice {

    public static int rollDie(int numberSidedDie) {
        int result = (int) (Math.random()*numberSidedDie) + 1;
        return result;
    }

    /**
     * rolls the given number of the given die
     * @param numberSidedDie: the type of die to be rolled;
     * @param numberOfDice: how many of the given die to be rolled
     * @return the total of the dice roll
     */
    public static int rollDice(int numberSidedDie, int numberOfDice) {
        int total = 0;
        for(int i = 0; i < numberOfDice; i++) {
            total += rollDie(numberSidedDie);
        }
        return total;
    }

}
