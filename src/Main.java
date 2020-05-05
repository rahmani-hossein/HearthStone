import Util.ConfigLoader;
import swing.Controller;
import swing.MyFrame;

public class Main {
    public static void main(String[] args) {

        Main program = new Main(args);

    }
    public Main(String[] args){
      // ConfigLoader urls =ConfigLoader.getInstance(getConfigFile(args));
        MyFrame myFrame = new MyFrame();


    }
    private String getConfigFile(String[] args){
        String configAddress = "default";
        if(args.length > 1){
            configAddress = args[1];
        }else{
//            System.out.println(S);
            if(System.getenv("HEARTHSTONE_CONFIG")!= null && !System.getenv("HEARTHSTONE_CONFIG").isEmpty()){
                configAddress = System.getenv("HEARTHSTONE_CONFIG");
            }
        }
        return configAddress;
    }
}
