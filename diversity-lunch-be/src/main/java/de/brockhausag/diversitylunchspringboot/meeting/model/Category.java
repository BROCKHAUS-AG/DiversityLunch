package de.brockhausag.diversitylunchspringboot.meeting.model;

public enum Category {
    Project("Projekt"),
    AGE("Alter"),
    GENDER("Geschlechtliche Identität"),
    COUNTRY_OF_ORIGIN("Ethnische Herkunft"),
    RELIGION("Religion"),
    MOTHER_TONGUE("Muttersprache"),
    HOBBY("Hobby"),
    EDUCATION("Bildungsweg"),
    WORK_EXPERIENCE("Berufserfahrung"),
    DIET("Ernährung"),
    SOCIAL_BACKGROUND("Soziale Herkunft"),
    SOCIAL_BACKGROUND_DISCRIMINATION("Diskriminierung aufgrund sozialer Herkunft"),
    SEXUAL_ORIENTATION("Sexuelle Orientierung");

    private final String kind;

    Category(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }
}
