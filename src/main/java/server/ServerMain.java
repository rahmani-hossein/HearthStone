package server;

public class ServerMain {
    public static void main(String[] args) {
        ServerConstants serverConstants = ConstantsLoader.getInstance().getServerConstants();
        GameServer gameServer = new GameServer(serverConstants.getPort(),serverConstants);
        gameServer.setServerConstants(serverConstants);
        gameServer.start();
    }
}
