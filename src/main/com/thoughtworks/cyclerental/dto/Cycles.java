package com.thoughtworks.cyclerental.dto;

import java.util.ArrayList;

public class Cycles extends ArrayList<Cycle> {
    public Cycle cycleFor(int cycleId) {
        return stream().filter(c -> !c.isRented && c.id == cycleId).findFirst().get();
    }
}
