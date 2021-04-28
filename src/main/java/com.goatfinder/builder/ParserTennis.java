package com.goatfinder.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParserTennis {


    public void read() {

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\TennisAllTime.csv"));
             Stream<String> line = reader.lines()) {

            List<String[]> allLines = line.map(lines -> lines.split(",")).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHeader(String colName, List<String[]> list) {
        for (int i = 0; i < list.get(0).length; i++) {

        }
        return 0;

    }
}