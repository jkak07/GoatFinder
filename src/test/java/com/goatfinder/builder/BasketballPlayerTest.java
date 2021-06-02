package com.goatfinder.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;

public class BasketballPlayerTest {
    BasketballPlayer player1;
    BasketballPlayer player2;


    @BeforeEach
    @Test
    @DisplayName("Create basketball player")
    public void testDatasetUp() {
        GoatStats stats = new GoatStats();

        player1 = new BasketballPlayer("Mount","PG", 22, stats);
        player2 = new BasketballPlayer("Adam","PG", 44, stats);
    }

    @Test
    @DisplayName("correct position")
    public void testPosition(){

        Assertions.assertAll(()->Assertions.assertEquals("PG",player1.getPosition()));
    }

    @Test
    @DisplayName("Goat case test")
    public void testGoatCase() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(player1.goatCase().contains("And I haven't even reached the twilight of my career.")),
                () -> Assertions.assertTrue(player2.goatCase().contains("Ask any of my peers, they know who I am, been in this business for years!")));

    }

}
