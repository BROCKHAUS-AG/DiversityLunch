package de.brockhausag.diversitylunchspringboot.profile.modelTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HobbyEnumTest {

    @Test
    void testGetCategory_ShouldReturnRightCategory() {
        Hobby hobby = Hobby.ACTING;
        String category = hobby.getCategory();
        assertEquals("KULTUR", category);
    }

    @Test
    void testGetKind_ShouldReturnRightKind() {
        Hobby hobby = Hobby.BALL_GAMES;
        String kind = hobby.getKind();
        assertEquals("BALLSPORT", kind);
    }
}
