package Interfaces;

import model.GamePlayer;
import model.card;

import java.util.ArrayList;

public interface Visitable {

    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, card target);
