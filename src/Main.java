public class Main {
    public static void main(String[] args) {

        String banner = """
                            _      _____         _              \s
                           / \\    |_   _|____  _| |_            \s
                          / _ \\     | |/ _ \\ \\/ / __|           \s
                         / ___ \\    | |  __/>  <| |_            \s
                    _   /_/ _ \\_\\   |_|\\___/_/\\_\\\\__|           \s
                   / \\   __| |_   _____ _ __ | |_ _   _ _ __ ___\s
                  / _ \\ / _` \\ \\ / / _ \\ '_ \\| __| | | | '__/ _ \\
                 / ___ \\ (_| |\\ V /  __/ | | | |_| |_| | | |  __/
                /_/   \\_\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|
                """;

        System.out.println(banner);

        new GameLoop();

    }
}

// test commit