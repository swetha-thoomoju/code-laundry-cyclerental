package com.thoughtworks.cyclerental.dto;

import java.util.ArrayList;

/**
 * Created by AccessQA on 03/08/19.
 */
public class Cycles extends ArrayList<Cycle> {
    public Cycle cycleFor(int cycleId) {
        return this.stream().filter(c -> !c.isRented && c.id == cycleId).findFirst().get();
    }
}
