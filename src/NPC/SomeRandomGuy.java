package NPC;

public class SomeRandomGuy extends NPC {
    public SomeRandomGuy(String name, boolean intro) {
        super(name, intro);
    }

// It's a dummy NPC which will never be used into the real scenario

    @Override
    public void introductoryMessage() {
        System.out.println("Bro, what do you want?\nYou are cringe!");
    }

    @Override
    public void askQuestion(String question) {
        System.out.println("Bro. If you don't want to spar with me don't bother me");
    }
}