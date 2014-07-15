package objects;

import java.util.ArrayList;

/**
 * Created by Zander on 7/14/2014.
 */
public class Scorecard {
    private ArrayList<Match> matches;
    private ArrayList<Colors> colors;
    private Themes theme;

    public Scorecard () {
        this.matches = new ArrayList<Match>();
        this.colors = new ArrayList<Colors>();
    }

    public void AddMatch (Match match) {
        this.matches.add(match);
    }
    public ArrayList<Match> GetMatches () {
        return this.matches;
    }

    public void SetColors (ArrayList<Colors> colors) {
        this.colors = colors;
    }
    public ArrayList<Colors> GetColors () {
        return this.colors;
    }

    public void SetTheme (Themes theme) {
        this.theme = theme;
    }
    public Themes GetTheme () {
        return this.theme;
    }
}
