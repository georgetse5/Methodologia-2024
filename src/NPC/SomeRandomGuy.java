package NPC;

public class SomeRandomGuy extends NPC {
    public SomeRandomGuy(String name, boolean intro) {
        super(name, intro);
    }

// It's a dummy NPC which will never be used into the real scenario
// Just for testing purposes

    @Override
    public void introductoryMessage() {
        if (!getIntro()) {
            System.out.println("What do you want?");
            System.out.println("I am not supposed to be here but here I am. So don't waste my time.");
            setIntro(true);
        } else {
            System.out.println("Ahh, you again!!");
        }
    }

    @Override
    public void askQuestion(String question) {
        System.out.println("I don't hear any questions because I don't have any answers!");
    }
}