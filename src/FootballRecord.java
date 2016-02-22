

/**
 * Store information about a football teams record. Stores
 * wins and losses.
 *
 */
public class FootballRecord {
    private String name;
    private int wins;
    private int losses;

    public FootballRecord(String n) {
        name = n;
    }

    public void win() {
        wins++;
    }

    public void lose() {
        losses++;
    }

    public double winPercent() {
        return 1.0 * wins / (wins + losses);
    }

    public boolean equals(Object other) {
        boolean result = other instanceof FootballRecord;
        if(result)
            result = name.equals( ((FootballRecord) other).name );
        return result;
    }

    public int hashCode() {
        return name.hashCode();
    }

    public int gamesPlayed() {
        return wins + losses;
    }
    
    public int getWins() {
        return wins;
    }
    
    public int getLosses() {
        return losses;
    }
    
    public String toString() {
        return name + ", wins: " + wins + ", losses: " + losses;
    }
}