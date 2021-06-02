package com.goatfinder.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class TennisAnalyzerTest {
    private IParser parser;

    @BeforeEach
    @Test
    public void testDatasetUp(){
        Map<String, Opinion> opinion = new HashMap<>();

        opinion.put("grandSlams", Opinion.STRONG_POSITIVE);
        opinion.put("tourFinals", Opinion.LOW_POSITIVE);
        opinion.put("altFinals", Opinion.LOW_POSITIVE);
        opinion.put("masters", Opinion.MEDIUM_POSITIVE);
        opinion.put("olympics", Opinion.STRONG_POSITIVE);
        opinion.put("titles", Opinion.MEDIUM_POSITIVE);
        opinion.put("weeksAtNo1", Opinion.STRONG_POSITIVE);

        String fileName = "data/tennisDataDummy.csv";
        parser = new TennisParser(fileName, opinion);

    }

    @Test
    @DisplayName("Tennis Calculator checker")
    public void testGoatCalculator(){
        GoatAnalyzer analyse = new TennisAnalyzer(parser);
        analyse.goatCalculator();
        Assertions.assertNotEquals(null, analyse.dataSet.getDataRows().get(3).getGoatStats().getGoatScore());
        Assertions.assertAll(() -> Assertions.assertTrue(analyse.dataSet.getDataRows().get(0).getGoatStats().getGoatScore()
                > analyse.dataSet.getDataRows().get(1).getGoatStats().getGoatScore()));
    }



}
