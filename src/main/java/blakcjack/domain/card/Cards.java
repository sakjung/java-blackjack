package blakcjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {
	public static final int ACE_ADDITIONAL_VALUE = 10;
	public static final int BLACKJACK_VALUE = 21;

	private final List<Card> cards = new ArrayList<>();

	public int calculateScore() {
		int score = calculateMinimumPossibleScore();
		int aceCount = calculateAceCount();

		while (hasNextPossibleScore(aceCount, score)) {
			score += ACE_ADDITIONAL_VALUE;
			aceCount--;
		}
		return score;
	}

	private int calculateMinimumPossibleScore() {
		return cards.stream()
				.mapToInt(Card::getCardNumberValue)
				.sum();
	}

	private int calculateAceCount() {
		return (int) cards.stream()
				.filter(Card::isAce)
				.count();
	}

	private boolean hasNextPossibleScore(final int aceCount, final int score) {
		return 0 < aceCount && (score + ACE_ADDITIONAL_VALUE) <= BLACKJACK_VALUE;
	}

	public void add(final Card card) {
		cards.add(card);
	}

	public List<Card> toList() {
		return Collections.unmodifiableList(cards);
	}

	public Card get(int index) {
		return cards.get(index);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Cards cards1 = (Cards) o;
		return Objects.equals(cards, cards1.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}
}
