package NPC;

public class MrAnderson extends NPC {
    public MrAnderson(String name, boolean intro) {
        super(name, intro);
    }

    @Override
    public void introductoryMessage() {
        System.out.println("Hello, my name is "
                + getName()
                + ". Also you can call me Mr Anderson.\nI am family's Smith butler, It's nice to meet you!"
                + "\nIf you have any question do not hesitate to ask.");
    }

    public void offerHelp() {
        System.out.println("How can I help you ?");
    }
}