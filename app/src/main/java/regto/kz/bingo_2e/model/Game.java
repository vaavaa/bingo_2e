package regto.kz.bingo_2e.model;

import java.util.LinkedList;

/**
 * Created by Андрей on 06.09.2016.
 */
public class Game {
    private boolean isGameLocked;
    private long GameStartTime;
    private long GameFinishTime;
    private String GameName;
    private int GameType;
    private int balance;
    private LinkedList<Droid> GameChips;

    public void Game(){
        GameChips = new LinkedList<>();
    }

    public boolean isGameLocked() {
        return isGameLocked;
    }

    public void setGameLocked(boolean gameLocked) {
        isGameLocked = gameLocked;
    }

    public long getGameStartTime() {
        return GameStartTime;
    }

    public void setGameStartTime(long gameStartTime) {
        GameStartTime = gameStartTime;
    }

    public long getGameFinishTime() {
        return GameFinishTime;
    }

    public void setGameFinishTime(long gameFinishTime) {
        GameFinishTime = gameFinishTime;
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }

    public int getGameType() {
        return GameType;
    }

    public void setGameType(int gameType) {
        GameType = gameType;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public LinkedList<Droid> getGameChips() {
        return GameChips;
    }

    public void setGameChips(LinkedList<Droid> gameChips) {
        GameChips = gameChips;
    }
}
