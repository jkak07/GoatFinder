/*GOAT = Greatest Of All Time - swimmer, teacher, dev, dancer,
This application consumes data pertinent to a subject matter person and establishes who is the best in that field
The methodology depends on input data and a series of stats which normalises it*/
package com.goatfinder.runner ;

import com.goatfinder.builder.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GoatRunner {

    public static Map<String, Opinion> setOpinionsBasketball(){
        Map<String, Opinion> opinions = new HashMap<>();

        opinions.put("3P%", Opinion.LOW_POSITIVE);
        opinions.put("FTA", Opinion.LOW_POSITIVE);
        opinions.put("TRB", Opinion.STRONG_POSITIVE);
        opinions.put("AST", Opinion.STRONG_POSITIVE);
        opinions.put("STL", Opinion.STRONG_POSITIVE);
        opinions.put("BLK", Opinion.LOW_POSITIVE);
        opinions.put("TOV", Opinion.MEDIUM_NEGATIVE);
        opinions.put("PF", Opinion.LOW_NEGATIVE);
        opinions.put("PTS", Opinion.STRONG_POSITIVE);

        return opinions;
}


    public static void main(String[] args) {


        //Normal Instantiation
        /*String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";
        IGoatFactory players = new GoatMaker();
        IParser nba = new BasketballParser(fileName, setOpinionsBasketball(), players);
        GoatAnalyzer analyse = new BasketballAnalyzer(nba);
        IGoatDisplayer nbaResults = new GoatFinder(analyse);
        nbaResults.displayGoats(20);/*

        //GoatFactories
        /*String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";
        IGoatFactory players = new GoatMaker();
        IParser nbaData = GoatMaker.parseBasketBallData(fileName, setOpinionsBasketball(),players);
        GoatAnalyzer analysed = GoatAnalyzer.BasketBallAnalyzer(nbaData);
        IGoatDisplayer nbaResults = GoatMaker.goatResults(analysed);
        nbaResults.displayGoats(20);*/

        /*ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext2.xml");

        GoatFinder nbaResults = appContext.getBean("Finder", GoatFinder.class);
        nbaResults.displayGoats(20);*/

        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext2.xml");

        GoatFinder nbaResults = appContext.getBean("Finder", GoatFinder.class);
        nbaResults.displayGoats(20);



    }


}
