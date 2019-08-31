import java.util.Random; //import java random

public class Card {

    int cardNumber;  //define attribute card number (would be random 1 - 13)
    int cardColorNumber; //define attribute color card number (random 1 - 4) color (diamonds, spades, hearts, clubs)
    int cardValue; //define attribute blackjack card value

    String totalCard = ""; //define attribute card such as 10 of hearts

    Random r = new Random(); //defining random var


    Card () {
        cardNumber = r.nextInt(13) + 1; //set card number to random number from 1 - 13
        cardColorNumber = r.nextInt(4) + 1; //set card color to random number from 1 - 4

        if (cardNumber == 1) { //if card number is one (means card is an ace)
            totalCard += "Ace"; //add "Ace" to totalCard attribute
            cardValue = 0; //set card value to be 0 because value would be changed later (decided by user)
        } else if (cardNumber == 11) { //card is a jack
            totalCard += "Jack"; //add "Jack" to totalCard attribute
            cardValue = 10; //set card value to 10
        } else if (cardNumber == 12) { //card is queen
            totalCard += "Queen"; //add "Queen" to totalCard attribute
            cardValue = 10; //set card value to 10
        } else if (cardNumber == 13) { //card is king
            totalCard += "King"; //add "King" to totalCard attribute
            cardValue = 10; //set card value to 10
        } else { //means card is a normal number
            totalCard += Integer.toString(cardNumber); //add the number to totalCard attribute
            cardValue = cardNumber;
        }

        totalCard += " of "; //add " off " to totalCard attribute, (an example of a card is "King of Spades, or 10 of Diamonds")

        if (cardColorNumber == 1) //if cardColorNumber is 1 (means color is spades)
            totalCard += "Spades"; //add Spades to totalCard
        if (cardColorNumber == 2) //if cardColorNumber is 2 (means color is hearts)
            totalCard += "Hearts"; //add Hearts to totalCard
        if (cardColorNumber == 3) //if cardColorNumber is 3 (means color is clubs)
            totalCard += "Clubs"; //add Clubs to totalCard
        if (cardColorNumber == 4) //if cardColorNumber is 4 (means color is diamonds)
            totalCard += "Diamonds"; //add Diamonds to totalCard
    }


    String getCard () { //function for getting the card
        return totalCard; //returning string attribute totalCard
    }

    int getValueOfCard () { //function for getting value of the card
        return cardValue; //returning int value of the card
    }


}
