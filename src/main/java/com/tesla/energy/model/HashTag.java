package com.tesla.energy.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum HashTag {

    ONE(1, "#one"),
    TWO(2, "#two"),
    THREE(3, "#three"),
    FOUR(4, "#four"),
    FIVE(5, "#five"),
    SIX(6, "#six"),
    SEVEN(7, "#seven"),
    EIGHT(8, "#eight"),
    NINE(9, "#nine"),
    TEN(10, "#ten");


    private static Map<String, HashTag> lookup = new HashMap<>();

    static {
        lookup.put("#one", ONE);
        lookup.put("#two", TWO);
        lookup.put("#three", THREE);
        lookup.put("#four", FOUR);
        lookup.put("#five", FIVE);
        lookup.put("#six", SIX);
        lookup.put("#seven", SEVEN);
        lookup.put("#eight", EIGHT);
        lookup.put("#nine", NINE);
        lookup.put("#ten", TEN);
    }

    private final int code;
    private final String hashTag;

    HashTag(int code, String hashTag) {
        this.code = code;
        this.hashTag = hashTag;
    }

    public static Optional<HashTag> get(String hashtagCode) {
        return Optional.ofNullable(lookup.get(hashtagCode));
    }

    public int getCode() {
        return code;
    }


}
