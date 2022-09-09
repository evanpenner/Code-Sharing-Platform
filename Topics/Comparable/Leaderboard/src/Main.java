import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Score implements Comparable<Score> {
    private final String player;
    private final int totalScore;

    public Score(String player, int totalScore) {
        this.player = player;
        this.totalScore = totalScore;
    }

    public String getPlayer() {
        return player;
    }

    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public String toString() {
        return player + '=' + totalScore;
    }

    @Override
    public int compareTo(Score score) {
        // your code here
        if (this.getTotalScore() == score.getTotalScore()) {
            return this.getPlayer().compareTo(score.getPlayer());
        }
        return Integer.compare(this.getTotalScore(), score.getTotalScore());
    }
}

// do not change the code below
class Main {

    public static void main(String[] args) {
        Instant instant  = Instant.EPOCH;
        instant.minus(Period.ofDays(1));

        System.out.println(instant.minus(Duration.ofDays(32)).atZone(ZoneId.of("GMT+6")));
        Scanner sc = new Scanner(System.in);
        List<Score> scores = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] input = sc.nextLine().split(" ");
            Score score = new Score(input[0], Integer.parseInt(input[1]));
            scores.add(score);
        }

        Collections.sort(scores);
        System.out.println(scores);
    }
}