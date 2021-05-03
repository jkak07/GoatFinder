package com.goatfinder.builder;

import java.util.List;

public interface IGoatDisplayer {

    void displayGoats(int x);
    List<? extends IGoat> getResults();
}
