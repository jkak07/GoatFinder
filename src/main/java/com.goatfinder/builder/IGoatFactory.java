package com.goatfinder.builder;

import java.util.List;
import java.util.Map;

public interface IGoatFactory {

    IGoat createBasketballPlayer1(String[] playerInfo, Map<String, Opinion> goatFields, String[] colNames, Map<String, List<Double>> dataCols);
}
