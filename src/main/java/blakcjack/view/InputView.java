package blakcjack.view;

import blakcjack.domain.participant.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
	public static final String YES = "y";
	public static final String NO = "n";
	private static final Scanner SCANNER = new Scanner(System.in);

	public static List<String> takePlayerNamesInput() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return Arrays.stream(SCANNER.nextLine().split(","))
				.map(String::trim)
				.collect(Collectors.toList());
	}

	public static boolean isYes(Player player) {
		try {
			String name = player.getName();
			System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
			String input = SCANNER.nextLine().trim();
			validate(input);
			return YES.equals(input);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return isYes(player);
		}
	}

	private static void validate(final String input) {
		if (!YES.equals(input) && !NO.equals(input)) {
			throw new IllegalArgumentException(YES + "나 " + NO + "만 입력해주세요.");
		}
	}
}
