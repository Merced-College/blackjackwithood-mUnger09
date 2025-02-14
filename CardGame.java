//Dylan Gongora, Matias Unger-Ramirez, Kirat Kaur

//package cardGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {

	private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
	private static ArrayList<Card> playerCards = new ArrayList<Card>();
	public static Card newCard;

	public static void main(String[] args) {
		Scanner input = null;
		try {
			input = new Scanner(new File("cards.txt"));
		} catch (FileNotFoundException e) {
			// error
			System.out.println("error");
			e.printStackTrace();
		}

		while(input.hasNext()) {
			String[] fields  = input.nextLine().split(",");
			//public Card(String cardSuit, String cardName, int cardValue, String cardPicture) {
			newCard = Card.createCard(fields[0], fields[1].trim(), Integer.parseInt(fields[2].trim()), fields[3]);
			deckOfCards.add(newCard);	
		}

		shuffle();

		//for(Card c: deckOfCards)
			//System.out.println(c);

		//deal the player 5 cards
		for(int i = 0; i < 4; i++) {
			playerCards.add(deckOfCards.remove(i));
		}
		
		System.out.println("players cards");
		for(Card c: playerCards)
			System.out.println(c);

		System.out.println("pairs is " + checkFor2Kind());

	}//end main

	//OUR CLASS
	public static class Card {
		private String suit;
		private String name;
		private int value;
		private String picture;
		public static Card createCard(String cardSuit, String cardName, int cardValue, String cardPicture) {
			return new Card(cardSuit, cardName, cardValue, cardPicture);
		}
		public Card(String cardSuit, String cardName, int cardValue, String cardPicture) {		
			this.suit = cardSuit;
			this.name = cardName;
			this.value = cardValue;
			this.picture = cardPicture;
		}
		public void setSuit(String cardSuit) {
			this.suit = cardSuit;
		}
		public void setName(String cardName) {
			this.name = cardName;
		}
		public void setValue(int cardValue) {
			this.value = cardValue;
		}
		public void setPicture(String cardPicture) {
			this.picture = cardPicture;
		}
		public String getSuit() {
			return this.suit;
		}
		public String getName() {
			return this.name;
		}
		public int getValue() {
			return this.value;
		}
		public String getPicture() {
			return this.picture;
		}
		public String toString() {
    		return name + " of " + suit + " (" + picture + ")";
		}
	}

	public static void shuffle() {
		//shuffling the cards by deleting and reinserting
		for (int i = 0; i < deckOfCards.size(); i++) {
			int index = (int) (Math.random()*deckOfCards.size());
			Card c = deckOfCards.remove(index);
			//System.out.println("c is " + c + ", index is " + index);
			deckOfCards.add(c);
		}
	}

	//FIXED TWO OF A KIND METHOD, wasn't checking cards correctly
	//check for 2 of a kind in the players hand
	public static boolean checkFor2Kind() {
		int count = 0;
		for(int i = 0; i < playerCards.size() - 1; i++) {
			Card current = playerCards.get(i);
			Card next = playerCards.get(i+1);
			
			String currentCard = current.getName();
			String nextCard = next.getName();
			
			for(int j = i+1; j < playerCards.size(); j++) {
				next = playerCards.get(j);
				nextCard = next.getName();
				//System.out.println(" comparing " + current);
				//System.out.println(" to " + next);
				if(currentCard.equals(nextCard))
					count++;
			}//end of inner for
			if(count >= 1)
				return true;

		}//end outer for
		return false;

	}
}//end class
