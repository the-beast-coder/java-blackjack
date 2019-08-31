import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in); //defining scanner

        boolean stop = false;

        int userScore = 0; //defining score for player 0
        int dealerScore = 0; //defining score for dealer 0

        System.out.println("The game uses multiple decks of cards (so cards can be repeated)");

        while (!stop) { //keep playing until meant to stop
            List<Card> playerCards = new ArrayList<>(); //list of players cards
            List<Card> dealerCards = new ArrayList<>(); //list of dealers cards

            playerCards.add(new Card()); //add a card to players deck
            playerCards.add(new Card()); //add another card to players deck

            boolean gameShouldBeOver = false;
            //means player has perfect blackjack
            if ((playerCards.get(0).cardValue == 0 && playerCards.get(1).cardValue == 10) || (playerCards.get(0).cardValue == 10 && playerCards.get(0).cardValue == 0)) {
                System.out.println("You got a perfect blackjack (an ace and a 10) you won!");
                userScore ++; //adding point to user score
                gameShouldBeOver = true; //setting game should be over because dealer won as it got a perfect blackjack
            }

            dealerCards.add(new Card()); //add a card to dealers deck
            dealerCards.add(new Card()); //add another card to dealers deck

            //means dealer has perfect blackjack
            if ((dealerCards.get(0).cardValue == 0 && dealerCards.get(1).cardValue == 10) || (dealerCards.get(0).cardValue == 10 && dealerCards.get(0).cardValue == 0)) {
                System.out.println("The dealer got a perfect blackjack (an ace and a 10) the dealer won!");
                dealerScore ++; //adding point to dealer's score
                gameShouldBeOver = true; //setting game should be over because dealer won as it got a perfect blackjack
            }

            if (!gameShouldBeOver) { //means game shouldn't be over
                System.out.println("One of the dealer's card is: " + dealerCards.get(0).getCard()); //let the user see one of the dealers cards

                boolean roundOver = false; //boolean to see if round is over or not
                boolean userPlaying = true; //boolean to see whether dealer is currently playing or user is playing

                boolean userWins; //did the user win or the dealer win

                int playerTotalCardsValue = 0; //users total cards value
                int dealerTotalCardsValue = 0; //dealers total cards value

                while (!roundOver) { //keep playing until either user or dealer is playing
                    if (userPlaying == true) { //if its users chance

                        playerTotalCardsValue = 0;

                        System.out.println("These are your cards: ");
                        for (int i = 0; i < playerCards.size(); i++) { //go through all player's cards
                            System.out.println(playerCards.get(i).getCard()); //print the card
                            if (playerCards.get(i).getValueOfCard() == 0) { //means card is an ace
                                System.out.println("You have an ace, do you want a 1 or an 11?");
                                int oneOrEleven = input.nextInt();
                                if (oneOrEleven == 1)
                                    playerCards.get(i).cardValue = 1; //means user wants card to be a 1
                                else
                                    playerCards.get(i).cardValue = 11; //means user wants card to be an 11
                            }
                            playerTotalCardsValue += playerCards.get(i).getValueOfCard(); //add this cards value to the totalCardsValue int
                        }

                        System.out.println("Your cards total value is: " + playerTotalCardsValue); //print out the total value of cards

                        if (playerTotalCardsValue > 21) { //if users total cards value is more than 21 user should lose
                            roundOver = true; //end the round
                            userWins = false; //set user win to false meaning that he lost
                            dealerScore ++;
                            System.out.println("BUST! Your cards are over 21. You lose."); //print loss message
                        } else { //if users total card value is less than 21 keep playing normally
                            System.out.println("Do you want to hit or stay:"); //ask if user wants to hit and get another card or stay
                            String hitOrStay = input.nextLine().toLowerCase(); //get input from user whether he wants to hot pr stay
                            if (hitOrStay.equals("hit"))
                                playerCards.add(new Card()); //add a card to playerCards array
                            else if (hitOrStay.equals("stay"))
                                userPlaying = false; //else set userPlaying to false and now the dealer will play
                        }

                    } else {
                        //dealer is playing
                        for (int i = 0; i < dealerCards.size(); i++) { //go through all elements in list
                            if (dealerCards.get(i).cardValue == 0) { //if card is ace
                                dealerCards.get(i).cardValue = 11; //set ace card value to 11
                            }
                        }
                        while (dealerTotalCardsValue < 17) { //if the dealer's total cards value is less than 17
                            dealerCards.add(new Card()); //add a new card to the dealerCard array
                            dealerTotalCardsValue = 0; //set dealer's total cards value to 0
                            for (int i = 0; i < dealerCards.size(); i++) { //go through all elements in dealer cards
                                dealerTotalCardsValue += dealerCards.get(i).getValueOfCard(); //add value to dealer's total card int
                            }
                        }
                        dealerTotalCardsValue = 0; //set dealer's total cards value to 0
                        for (int i = 0; i < dealerCards.size(); i++) { //go through all elements in dealer cards
                            if (dealerCards.get(i).cardValue == 0) { //if card is ace
                                if (dealerTotalCardsValue <= 10) { //if total value of dealer's cards is less than 10
                                    dealerCards.get(i).cardValue = 11; //set ace value to 11
                                } else { //else set ace value to 1
                                    dealerCards.get(i).cardValue = 1; //set ace value to 1
                                }
                            }
                            dealerTotalCardsValue += dealerCards.get(i).getValueOfCard(); //add value to dealer's total card int
                        }
                        if (dealerTotalCardsValue > 21) { //if dealer's total card value is more than 21 (means dealer should be bust)
                            System.out.println("The dealer has the cards:");
                            for (int i = 0; i < dealerCards.size(); i++) { //go through all elements in dealer's cards
                                System.out.println(dealerCards.get(i).getCard()); //print dealer's cards
                            }
                            userWins = true;  //user wins
                            userScore ++;
                            roundOver = true;
                            System.out.println("Dealer got over 21. You win!");
                        }
                        if (dealerTotalCardsValue >= 17 && dealerTotalCardsValue <= 21) { //if user is more than 17 and less than 21
                            System.out.println("The dealer has the cards:");
                            for (int i = 0; i < dealerCards.size(); i++) { //go through all elements in dealer's cards
                                System.out.println(dealerCards.get(i).getCard()); //print dealer's cards
                            }
                            if (dealerTotalCardsValue > playerTotalCardsValue) { //means dealer has better cards than user
                                System.out.println("Dealer wins! The dealer got a total value of " + dealerTotalCardsValue);
                                userWins = false; //dealer wins
                                dealerScore ++;
                                roundOver = true;
                            } else if (dealerTotalCardsValue < playerTotalCardsValue) { //means player has better cards than user
                                System.out.println("You win! The dealer got a total value of " + dealerTotalCardsValue);
                                userWins = true; //player wins
                                userScore ++;
                                roundOver = true;
                            } else { //means player has equal cards with user
                                System.out.println("You and the dealer draw.");
                                userScore ++;
                                dealerScore ++;
                                roundOver = true;
                            }
                        }
                    }
                }
            }


            System.out.println("Your score is: " + userScore + " the dealer's score is: " + dealerScore);
            System.out.println("Do you want to play again (y or n)?"); //if user wants to play another round of blackjack
            String playAgain = input.nextLine().toLowerCase(); //get input from user
            if (playAgain.equals("n"))
                stop = true; //if doesn't want to play set stop = true
            else
                stop = false; //if user does want to play set stop = false
        }
    }
}