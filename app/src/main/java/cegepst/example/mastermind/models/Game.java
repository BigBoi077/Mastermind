package cegepst.example.mastermind.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {

    private int MAX_ATTEMPTS = 12;
    private String[] colorsArray;
    private String[] randomColorArray;
    private String difficulty;
    private int nbrColorCombination;
    private int nbrAttempts;

    public Game() {
        difficulty = "";
        nbrColorCombination = 0;
        nbrAttempts = 0;
        colorsArray = new String[]{"B", "W", "Y", "R", "BL", "G"};
    }

    protected Game(Parcel in) {
        MAX_ATTEMPTS = in.readInt();
        colorsArray = in.createStringArray();
        randomColorArray = in.createStringArray();
        difficulty = in.readString();
        nbrColorCombination = in.readInt();
        nbrAttempts = in.readInt();
    }

    public boolean isMaxedAttempts() {
        return nbrAttempts < MAX_ATTEMPTS;
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

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(MAX_ATTEMPTS);
        parcel.writeStringArray(colorsArray);
        parcel.writeStringArray(randomColorArray);
        parcel.writeString(difficulty);
        parcel.writeInt(nbrColorCombination);
        parcel.writeInt(nbrAttempts);
    }
}
