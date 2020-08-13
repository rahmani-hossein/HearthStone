package server;

public class ConstantsLoader {

    private ServerConstants serverConstants;
    private static ConstantsLoader constantsLoader;
    private ConstantsLoader(){
        serverConstants = new ServerConstants();
    }
    public static ConstantsLoader getInstance(){
        if(constantsLoader == null){
            constantsLoader = new ConstantsLoader();
        }
        return constantsLoader;
    }

    public ServerConstants getServerConstants() {
        return serverConstants;
    }

    public void setServerConstants(ServerConstants serverConstants) {
        this.serverConstants = serverConstants;
    }
}
