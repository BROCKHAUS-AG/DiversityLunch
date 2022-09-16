package de.brockhausag.diversitylunchspringboot.profile.model;

public enum Hobby {
    BALL_GAMES("SPORT", "BALLSPORT"),
    ARCHERY("SPORT", "BOGENSCHIESSEN"),
    DART("SPORT", "DART"),
    JOGGING("SPORT", "JOGGEN"),
    MARTIAL_ARTS("SPORT", "KAMPFSPORT"),
    CLIMBING("SPORT", "KLETTERN"),
    CYCLING("SPORT", "RADSPORT"),
    SPORTS("SPORT", "SPORT"),
    DANCING("SPORT", "TANZEN"),
    YOGA("SPORT", "YOGA"),
    DIY("KREATIVES", "DIY"),
    PHOTOGRAPHY("KREATIVES", "FOTOGRAFIE"),
    COOKING("KREATIVES", "KOCHEN"),
    ART("KREATIVES", "MALEN"),
    MUSIC("KREATIVES", "MUSIK"),
    FISHING("NATUR", "ANGELN"),
    CAMPING("NATUR", "CAMPING"),
    GARDENING("NATUR", "GAERTNERN"),
    HUNTING("NATUR", "JAGEN"),
    HIKE("NATUR", "WANDERN"),
    MOVIES("UNTERHALTUNG", "FILME"),
    GAMING("UNTERHALTUNG", "GAMING"),
    BOARD_GAMES("UNTERHALTUNG", "GESELLSCHAFTSSPIELE"),
    READING("UNTERHALTUNG", "LESEN"),
    PUZZLING("UNTERHALTUNG", "PUZZELN"),
    TRAVELING("KULTUR", "REISEN"),
    ACTING("KULTUR", "THEATER"),
    OTHER("SONSTIGES", "SONSTIGES");

    private final String category;
    private final String kind;

    Hobby(String category, String kind) {
        this.category = category;
        this.kind = kind;
    }

    public String getCategory() {
        return category;
    }

    public String getKind() {
        return kind;
    }

}
