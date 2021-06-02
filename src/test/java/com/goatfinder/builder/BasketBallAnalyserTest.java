package com.goatfinder.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class BasketBallAnalyserTest {

    private IParser parser;


    @BeforeEach
    @Test
    public void testDatasetUp(){
        Map<String, Opinion> opinion = new HashMap<>();

        opinion.put("MP", Opinion.MEDIUM_POSITIVE);
        opinion.put("3P%", Opinion.LOW_POSITIVE);
        opinion.put("FTA", Opinion.LOW_POSITIVE);
        opinion.put("TRB", Opinion.STRONG_POSITIVE);
        opinion.put("AST", Opinion.STRONG_POSITIVE);
        opinion.put("STL", Opinion.STRONG_POSITIVE);
        opinion.put("BLK", Opinion.LOW_POSITIVE);
        opinion.put("TOV", Opinion.MEDIUM_NEGATIVE);
        opinion.put("PF", Opinion.LOW_NEGATIVE);
        opinion.put("PTS", Opinion.STRONG_POSITIVE);

        String fileName = "data/nba2021test2.txt";
        IGoatMaker goatMakerSpy = Mockito.spy(GoatMaker.class);
        parser = new BasketballParser(fileName, opinion,goatMakerSpy);


    }


    @Test
    @DisplayName("Goat Calculator checker")
    public void testGoatCalculator(){
        GoatAnalyzer analyseNBA = new BasketballAnalyzer(parser);
        analyseNBA.goatCalculator();
        Assertions.assertNotEquals(null, analyseNBA.dataSet.getDataRows().get(3).getGoatStats().getGoatScore());
        Assertions.assertAll(() -> Assertions.assertTrue(analyseNBA.dataSet.getDataRows().get(0).getGoatStats().getGoatScore()
                < analyseNBA.dataSet.getDataRows().get(1).getGoatStats().getGoatScore()));
    }

}
