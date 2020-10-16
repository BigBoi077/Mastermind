package cegepst.example.mastermind.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MastermindGame implements Parcelable {

    private final static int MAX_ATTEMPTS = 12;

    private String[] colorsArray;
    private String[] randomColorArray;
    private String[] playerColorArray;

    private String difficulty;
    private String playerColorCombination;

    private int nbrColorCombination;
    private int nbrAttempts;

    public MastermindGame() {
        difficulty = "";
        nbrColorCombination = 0;
        nbrAttempts = 0;
        colorsArray = new String[]{"B", "W", "Y", "R", "BL", "G"};
    }

    protected MastermindGame(Parcel in) {
        colorsArray = in.createStringArray();
        randomColorArray = in.createStringArray();
        playerColorArray = in.createStringArray();
        difficulty = in.readString();
        playerColorCombination = in.readString();
        nbrColorCombination = in.readInt();
        nbrAttempts = in.readInt();
    }

    public boolean isMaxedAttempts() {
        return nbrAttempts >= MAX_ATTEMPTS;
    }

    public void setCombinationsByDifficulty() {
        if (difficulty.equals("Classic")) {
            this.nbrColorCombination = 4;
        } else {
            this.nbrColorCombination = 5;
        }
    }

    public void generateRandomColorArray() {
        this.randomColorArray = new String[nbrAttempts];
        this.randomColorArray = Random.generateRandomArray(this);
    }

    public String[] getRandomColorArray() {
        return randomColorArray;
    }

    public void setRandomColorArray(String[] randomColorArray) {
        this.randomColorArray = randomColorArray;
    }

    public String[] getPlayerColorArray() {
        return playerColorArray;
    }

    public void setPlayerColorArray(String[] playerColorArray) {
        this.playerColorArray = playerColorArray;
    }


    public String[] getColorsArray() {
        return colorsArray;
    }

    public void setColorsArray(String[] colorsArray) {
        this.colorsArray = colorsArray;
    }

    public int getNbrAttempts() {
        return nbrAttempts;
    }

    public void setNbrAttempts(int nbrAttempts) {
        this.nbrAttempts = nbrAttempts;
    }

    public int getNbrColorCombination() {
        return nbrColorCombination;
    }

    public void setNbrColorCombination(int nbrColorCombination) {
        this.nbrColorCombination = nbrColorCombination;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getPlayerColorCombination() {
        return playerColorCombination;
    }

    public void setPlayerColorCombination(String playerColorCombination) {
        this.playerColorCombination = playerColorCombination;
    }

    public static final Creator<MastermindGame> CREATOR = new Creator<MastermindGame>() {
        @Override
        public MastermindGame createFromParcel(Parcel in) {
            return new MastermindGame(in);
        }

        @Override
        public MastermindGame[] newArray(int size) {
            return new MastermindGame[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(colorsArray);
        parcel.writeStringArray(randomColorArray);
        parcel.writeStringArray(playerColorArray);
        parcel.writeString(difficulty);
        parcel.writeString(playerColorCombination);
        parcel.writeInt(nbrColorCombination);
        parcel.writeInt(nbrAttempts);
    }
}