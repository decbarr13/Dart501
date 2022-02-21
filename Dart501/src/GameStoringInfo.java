

public class GameStoringInfo {
	boolean computerPlaying;
	String playerOneName;
	String playerTwoName;
	boolean legs;
	boolean sets;
	boolean firstTo;
	boolean bestOf;
	int numberOfGames;
	int computerAverage;
	int endingGameScore;
	int playerStart;

	public GameStoringInfo() {
		computerPlaying = true;
		playerOneName = "Dec";
		playerTwoName = null;
		legs = true;
		sets = false;
		firstTo = true;
		bestOf = false;
		numberOfGames = 0;
		computerAverage = 0;
		
	}

	public void endingGameScoring() {
		if(firstTo) {
			endingGameScore = numberOfGames;
		}
		else {
			int temp = numberOfGames/2;
			endingGameScore = temp+1;
		}
	}
}
