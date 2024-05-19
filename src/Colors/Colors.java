package Colors;

public class Colors {

    private final String ANSI_RESET;
    private final String ANSI_RED;
    private final String ANSI_GREEN;
    private final String ANSI_GOLD;
    private final String ANSI_CYAN;
    private final String ANSI_BLUE;

    public Colors() {
        this.ANSI_RESET = "\u001B[0m";
        this.ANSI_RED = "\u001B[0m";
        this.ANSI_GREEN = "\u001B[32m";
        this.ANSI_GOLD = "\u001B[33m";
        this.ANSI_CYAN = "\u001B[36m";
        this.ANSI_BLUE = "\u001B[34m";
    }

    public String reset() {
        return ANSI_RESET;
    }

    public String red() {
        return ANSI_RED;
    }

    public String green() {
        return ANSI_GREEN;
    }

    public String gold() {
        return ANSI_GOLD;
    }

    public String cyan() {
        return ANSI_CYAN;
    }

    public String blue() {
        return ANSI_BLUE;
    }
}
