package blakcjack.domain.participant;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;
import blakcjack.domain.name.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
	@DisplayName("플레이어 객체 생성 성공")
	@Test
	void create() {
		final Player player = new Player(new Name("pobi"));
		assertThat(player).isEqualTo(new Player(new Name("pobi")));
	}

	@DisplayName("카드 받기 성공")
	@Test
	void receiveCard() {
		final Player player = new Player(new Name("sakjung"));
		final Card card = Card.of(CardSymbol.CLUB, CardNumber.ACE);
		player.receiveCard(card);

		assertThat(player.getCards()).isEqualTo(Collections.singletonList(Card.of(CardSymbol.CLUB, CardNumber.ACE)));
	}

	@DisplayName("점수 계산 성공")
	@Test
	void score() {
		Player player = new Player(new Name("pobi"));

		player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.ACE));
		player.receiveCard(Card.of(CardSymbol.HEART, CardNumber.ACE));
		player.receiveCard(Card.of(CardSymbol.DIAMOND, CardNumber.ACE));
		player.receiveCard(Card.of(CardSymbol.CLUB, CardNumber.ACE));
		player.receiveCard(Card.of(CardSymbol.CLUB, CardNumber.FIVE));

		assertThat(player.calculateScore()).isEqualTo(19);
	}

	@DisplayName("21점 이하면 버스트 아니고 21점 초과일 시 버스트 처리 해주는지")
	@Test
	void isBust() {
		final Player player = new Player(new Name("pobi"));
		player.receiveCard(Card.of(CardSymbol.CLUB, CardNumber.KING));
		player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.QUEEN));
		assertThat(player.isBust()).isFalse();

		player.receiveCard(Card.of(CardSymbol.CLUB, CardNumber.TWO));
		assertThat(player.isBust()).isTrue();
	}
}
