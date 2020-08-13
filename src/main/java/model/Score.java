package model;

public class Score implements Comparable<Score> {

    private String name;
    private String state;
    private int cupNumber;

    public Score(String name, String state, int cupNumber) {
        this.name = name;
        this.state = state;
        this.cupNumber = cupNumber;
    }

    public Score(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCupNumber() {
        return cupNumber;
    }

    public void setCupNumber(int cupNumber) {
        this.cupNumber = cupNumber;
    }

    @Override
    public int compareTo(Score o) {

        if (cupNumber>o.cupNumber){
            return -1;
        }
        else if (cupNumber<o.cupNumber){
            return 1;
        }
        else {
            return 0;
        }
    }
}
