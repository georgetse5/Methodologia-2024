package NPC;

import Player.Player;

public class MrAnderson extends NPC {

    Player player;

    public MrAnderson(String name, boolean intro) {
        super(name, intro);
    }

    @Override
    public void introductoryMessage() {

        if (!getIntro()) {
            System.out.println("Hello, my name is "
                    + getName()
                    + ". Also you can call me Mr Anderson.\nI am family's Smith butler, It's nice to meet you!"
                    + "\nIf you have any questions do not hesitate to ask.");
            setIntro(true);
        } else {
            System.out.println("Hey " + player.getName() + ". How the investigation is going ?");
            System.out.println("If you have any questions do not hesitate to ask.");
        }
    }

    public void offerHelp() {
        System.out.println("How can I help you ?");
    }
}