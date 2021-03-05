package blakcjack.domain.card;

import blakcjack.domain.shufflestrategy.RandomShuffleStrategy;
import blakcjack.domain.shufflestrategy.ShuffleStrategy;

import java.util.Stack;

public class Deck {
	public static final String NO_CARD_ERROR = "카드가 없습니다.";
	private final Stack<Card> cards = new Stack<>();

	public Deck(final ShuffleStrategy shuffleStrategy) {
		generateCards();
		shuffleStrategy.shuffle(cards);
	}

	protected void generateCards() {
		for (final CardSymbol cardSymbol : CardSymbol.values()) {
			for (final CardNumber cardNumber : CardNumber.values()) {
				cards.add(Card.of(cardSymbol, cardNumber));
			}
		}
	}

	public Card drawCard() {
		if (cards.empty()) {
			throw new emptyDeckException(NO_CARD_ERROR);
		}
		return cards.pop();
	}
}
