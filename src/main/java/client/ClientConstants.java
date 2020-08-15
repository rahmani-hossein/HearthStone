package client;

import java.util.HashMap;

public class ClientConstants {

    private int port=8000;
    private int cardWidth = 200;
    private int cardHeigth = 285;
    private int sizeX = 100;
    private int sizeY = 135;
    private int space = 100;
    private int panelHeight = 1000;
    private int panelWidth = 1700;
    private int groundSpace = 50;
    private int handSpace = 35;
    private int maxYGame = 800;
    private int minYGame = 150;
    private int friendHeroY=700;
    private int GroundX=300;
    private int friendGroundY =600;
    private int freindHeroX = 1000;
    private int enemyHeroY=150;
    private int enemyHeroX=1000;
    private int enenmyGroundY=300;
    private int manaX=800;
    private int friendManaY=750;
    private int enemyManaY=200;
    private int noteSize=70;
    private int sizeBoardX=450;
    private int sizeBoardY=500;
    private int sad=100;
    private String[] passives = {"twiceDraw", "offCard", "freePower", "manaJump", "nurse"};
    private HashMap<String,Integer> target = new HashMap<>();


    public ClientConstants(){
        fill();
    }

    //getter & setter


    private void fill(){
        target.put("blazingBattlemage",0);
        target.put("hotAirballon",0);
        target.put("fireHawk",0);
        target.put("evasiveChimaera",0);
        target.put("amberWatcher",0);
        target.put("dreadScale",0);
        target.put("veranus",0);
        target.put("curioCollector",0);
        target.put("highPriestAmet",0);
        target.put("sathrovarr",1);
        target.put("securityRover",0);
        target.put("swampKingDred",0);
        target.put("tombWarden",0);
        target.put("decimation",0);
        target.put("malygosFireball",2);
        target.put("malygosFlamestrike",0);
        target.put("friendlySmith",0);
        target.put("fireBlast",2);
        target.put("arcaneShot",2);
        target.put("bookOfSpecters",0);
        target.put("flamestrike",0);
        target.put("learnDraconic",0);
        target.put("pharaohsBlessing",1);
        target.put("polymorph",2);
        target.put("sprint",0);
        target.put("strengthInNumbers",0);
        target.put("swarmOfLocusts",0);
        target.put("divineHymn",0);
        target.put("bloodRazor",0);
        target.put("fierywaraxe",0);
        target.put("bloodClaw",0);
        target.put("assassinBlade",0);
        target.put("arcaniteReaper",0);
        target.put("battleAxe",0);
        target.put("gearBlade",0);
        target.put("bloodFury",0);
        target.put("dragonClaw",0);
        target.put("ashBringer",0);
    }

    public HashMap<String, Integer> getTarget() {
        return target;
    }

    public void setTarget(HashMap<String, Integer> target) {
        this.target = target;
    }

    public String[] getPassives() {
        return passives;
    }

    public void setPassives(String[] passives) {
        this.passives = passives;
    }

    public int getCardWidth() {
        return cardWidth;
    }

    public void setCardWidth(int cardWidth) {
        this.cardWidth = cardWidth;
    }

    public int getCardHeigth() {
        return cardHeigth;
    }

    public void setCardHeigth(int cardHeigth) {
        this.cardHeigth = cardHeigth;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getPanelHeight() {
        return panelHeight;
    }

    public void setPanelHeight(int panelHeight) {
        this.panelHeight = panelHeight;
    }

    public int getPanelWidth() {
        return panelWidth;
    }

    public void setPanelWidth(int panelWidth) {
        this.panelWidth = panelWidth;
    }

    public int getGroundSpace() {
        return groundSpace;
    }

    public void setGroundSpace(int groundSpace) {
        this.groundSpace = groundSpace;
    }

    public int getHandSpace() {
        return handSpace;
    }

    public void setHandSpace(int handSpace) {
        this.handSpace = handSpace;
    }

    public int getMaxYGame() {
        return maxYGame;
    }

    public void setMaxYGame(int maxYGame) {
        this.maxYGame = maxYGame;
    }

    public int getMinYGame() {
        return minYGame;
    }

    public void setMinYGame(int minYGame) {
        this.minYGame = minYGame;
    }

    public int getFriendHeroY() {
        return friendHeroY;
    }

    public void setFriendHeroY(int friendHeroY) {
        this.friendHeroY = friendHeroY;
    }

    public int getGroundX() {
        return GroundX;
    }

    public void setGroundX(int groundX) {
        GroundX = groundX;
    }

    public int getFriendGroundY() {
        return friendGroundY;
    }

    public void setFriendGroundY(int friendGroundY) {
        this.friendGroundY = friendGroundY;
    }

    public int getFreindHeroX() {
        return freindHeroX;
    }

    public void setFreindHeroX(int freindHeroX) {
        this.freindHeroX = freindHeroX;
    }

    public int getEnemyHeroY() {
        return enemyHeroY;
    }

    public void setEnemyHeroY(int enemyHeroY) {
        this.enemyHeroY = enemyHeroY;
    }

    public int getEnemyHeroX() {
        return enemyHeroX;
    }

    public void setEnemyHeroX(int enemyHeroX) {
        this.enemyHeroX = enemyHeroX;
    }

    public int getEnenmyGroundY() {
        return enenmyGroundY;
    }

    public void setEnenmyGroundY(int enenmyGroundY) {
        this.enenmyGroundY = enenmyGroundY;
    }

    public int getManaX() {
        return manaX;
    }

    public void setManaX(int manaX) {
        this.manaX = manaX;
    }

    public int getFriendManaY() {
        return friendManaY;
    }

    public void setFriendManaY(int friendManaY) {
        this.friendManaY = friendManaY;
    }

    public int getEnemyManaY() {
        return enemyManaY;
    }

    public void setEnemyManaY(int enemyManaY) {
        this.enemyManaY = enemyManaY;
    }

    public int getNoteSize() {
        return noteSize;
    }

    public void setNoteSize(int noteSize) {
        this.noteSize = noteSize;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getSizeBoardX() {
        return sizeBoardX;
    }

    public void setSizeBoardX(int sizeBoardX) {
        this.sizeBoardX = sizeBoardX;
    }

    public int getSizeBoardY() {
        return sizeBoardY;
    }

    public void setSizeBoardY(int sizeBoardY) {
        this.sizeBoardY = sizeBoardY;
    }

    public int getSad() {
        return sad;
    }

    public void setSad(int sad) {
        this.sad = sad;
    }
}
