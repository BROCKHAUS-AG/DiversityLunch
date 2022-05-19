package de.brockhausag.diversitylunchspringboot.meeting.model;

import java.util.Arrays;
import java.util.List;

public enum Question {

    CUSTOMER1("Diversity matters! Inwiefern spielt gesellschaftliche Vielfalt an eurem Arbeitsplatz eine Rolle? " +
            "Und in welchen Bereichen vermisst ihr bei der BROCKHAUS AG oder in eurem Team mehr Vielfalt?",
            Category.CUSTOMER),
    CUSTOMER2("Könntet ihr euch vorstellen mit einer Person mit Autismus zusammen in einem Team zu arbeiten? " +
            "Welche Chancen und Herausforderungen seht ihr dabei für euch?",
            Category.CUSTOMER),
    AGE1("Was denkt ihr: Sind altersgemischte Teams innovativer und leistungsfähiger als altershomogene " +
            "Arbeitsgruppen?",
            Category.AGE),
    AGE2("Was könnt ihr von einem deutlich jüngeren oder älteren Kollegen_in lernen?", Category.AGE),
    AGE3("Welche Rolle spielt für euch Alter am Arbeitsplatz?", Category.AGE),
    AGE4("In welchen Situationen ist das Alter im Team von Bedeutung? Wann ist es unwichtig?", Category.AGE),
    GENDER1("Was denkt ihr: Wieso begeistern sich so wenige Frauen für die IT?", Category.GENDER),
    GENDER2("Wie unterschiedlich ticken Männer und Frauen?", Category.GENDER),
    GENDER3("Sind einige Geschlechter deutlich unterrepräsentiert, " +
            "werden die entsprechenden Mitarbeiter_innen oft unbewusst ausgegrenzt, " +
            "besonders behandelt oder nicht mitgedacht und berücksichtigt. " +
            "Ist euch das in eurem Arbeitsalltag schon einmal aufgefallen?", Category.GENDER),
    GENDER4("Glaub ihr, dass es einen Unterschied macht, wenn auf eine gendersensible Sprache im Arbeitsalltag " +
            "geachtet wird? " +
            "Wenn ja, welche? Wenn nicht, warum?", Category.GENDER),
    GENDER5("Was sind aus eurer Sicht Gründe dafür, dass Frauen weniger stark als Männer in " +
            "Führungspositionen vertreten sind? Wie findet ihr das?",
            Category.GENDER),
    COUNTRY_OF_ORIGIN1("Aus welchem Land stammt ihr und welche kulturellen Unterschiede, " +
            "aber auch welche Gemeinsamkeiten teilt ihr?",
            Category.COUNTRY_OF_ORIGIN),
    COUNTRY_OF_ORIGIN2("Welche Probleme bringt ein Migrationshintergrund mit sich, " +
            "wann wurdet ihr auf eure Herkunft reduziert oder genießt ihr dadurch Vorteile?",
            Category.COUNTRY_OF_ORIGIN),
    COUNTRY_OF_ORIGIN3("In welchen beruflichen Situationen ist ein interkulturelles Team eurer Meinung nach " +
            "besonders wichtig?", Category.COUNTRY_OF_ORIGIN),
    RELIGION1("Unternehmen setzen sich vermehrt für einen respektvollen und wertschätzenden Umgang im " +
            "Hinblick auf Religionszugehörigkeit und Weltanschauung ein. " +
            "Typische Beispiele sind die Berücksichtigung der Feiertage von unterschiedlichen Religionen, " +
            "die Einrichtung eines „Raums der Stille“ sowie Speisenangebote entsprechend der religiösen " +
            "Gepflogenheiten. " +
            "Fühlt ihr euch mit eurem Glauben bei der BROCKHAUS AG gut aufgehoben?", Category.RELIGION),
    RELIGION2("Was unterscheidet eure Religionen voneinander?", Category.RELIGION),
    RELIGION3("Welche Feiertage sind euch besonders wichtig und wie feiert ihr sie?", Category.RELIGION),
    RELIGION4("Welche Vorteile und welche Herausforderungen ergeben sich in einem Team durch verschiedene " +
            "Religionszugehörigkeiten der Beschäftigten?", Category.RELIGION),
    MOTHER_TONGUE1("Welche Sprache ist eure Muttersprache, wurdet ihr vielleicht sogar zweisprachig erzogen?",
            Category.MOTHER_TONGUE),
    MOTHER_TONGUE2("Was macht Mehrsprachigkeit mit Menschen, Öffentlichkeiten, Institutionen " +
            "und ihren Mitarbeiter_innen?", Category.MOTHER_TONGUE),
    MOTHER_TONGUE3("Was sagt die Anzahl der gesprochenen Sprachen über einen Menschen aus?",
            Category.MOTHER_TONGUE),
    HOBBY1("Gibt es Hobbys, die ihr teilt?", Category.HOBBY),
    HOBBY2("Bei welchem Hobby könnt ihr so richtig abschalten?", Category.HOBBY),
    HOBBY3("Welche Sportart/Welches Hobby wolltet ihr schon immer einmal ausprobieren?", Category.HOBBY),
    EDUCATION1("Wie sah euer Bildungsweg aus und was hat euren Bildungsweg beeinflusst?", Category.EDUCATION),
    EDUCATION2("Seid ihr der Meinung, dass die soziale Herkunft eines Menschen seinen beruflichen" +
            "Werdegang beeinflusst? Wenn ja, warum? Wenn nicht, warum nicht?", Category.EDUCATION),
    WORK_EXPERIENCE1("Wie lange bist du schon in deinem Beruf tätig?", Category.WORK_EXPERIENCE),
    WORK_EXPERIENCE2("Wenn ihr dieses mögliche Gesprächsthema ausgeliefert bekommen habt, " +
            "ist es sehr wahrscheinlich so, " +
            "dass einer von euch bereits viele Jahre Berufserfahrung vorzuweisen hat, " +
            "der andere Gesprächsteilnehmer nicht. An Zweiteren: Was kannst du von deinem Kollegen lernen?",
            Category.WORK_EXPERIENCE),
    WORK_EXPERIENCE3("Habt ihr das Mentoren-Programm in Anspruch genommen und wenn ja, wie hat euch " +
            "das geholfen?",
            Category.WORK_EXPERIENCE),
    DIET1("Kocht ihr gerne und wenn ja, was?", Category.DIET),
    DIET2("Was esst ihr während eures Gesprächs in der Mittagspause? Wieso ist die Wahl darauf gefallen?",
            Category.DIET),
    DIET3("Was ist euer Lieblingsessen?", Category.DIET),
    DIET4("Team Frisch oder Team Tiefkühlpizza?", Category.DIET);

    private final String kind;
    private final Category category;

    Question(String kind, Category category) {
        this.kind = kind;
        this.category = category;
    }

    public static List<Question> getAllQuestionsWithCategory(Category category){
        return Arrays
                .stream(Question.values())
                .filter(question -> question.category.equals(category))
                .toList();
    }

    public String getKind() {
        return kind;
    }

    public Category getCategory() {
        return category;
    }
}
