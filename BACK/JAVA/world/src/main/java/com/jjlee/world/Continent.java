package com.jjlee.world;

public enum Continent {
    AFRICA("아프리카"),
    ASIA("아시아"),
    EUROPE("유럽"),
    NORTH_AMERICA("북아메리카"),
    OCEANIA("오세아니아"),
    SOUTH_AMERICA("남아메리카");

    public final String nameKo;

    Continent(String nameKo) {
        this.nameKo = nameKo;
    }
}
