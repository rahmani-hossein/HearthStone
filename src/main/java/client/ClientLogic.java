package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Player;
import model.Request;

import javax.swing.*;

public class ClientLogic {

    private ObjectMapper objectMapper;

    public ClientLogic(){
        objectMapper=new ObjectMapper();
    }

    public void recieveBuy(Request request){
        if (request.getParameters().size()==0){
            Controller.getInstance().getGameState().setPlayer(getObject(Player.class,request.getBody()));
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(),"info","we successfully buy ",JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you can not buy this card ", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void receiveSell(Request request){
        if (request.getParameters().size()>0){
            Controller.getInstance().getGameState().setPlayer(getObject(Player.class,request.getBody()));
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(),"info","we successfully sell ",JOptionPane.INFORMATION_MESSAGE);

        }
        else {
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you can not sell this card ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private <A> A getObject(Class<A> aClass, String jsonValue){
        try {
            return objectMapper.readValue(jsonValue,aClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
