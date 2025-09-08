package com.roko.hotelpricefollower.scraper;

public enum FinnishMonth {
    TAMMI(1),
    HELMI(2),
    MAALIS(3),
    HUHTI(4),
    TOUKO(5),
    KESÄ(6),
    HEINÄ(7),
    ELO(8),
    SYYS(9),
    LOKA(10),
    MARRAS(11),
    JOULU(12);

    private int monthNumber;

    FinnishMonth(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public int getMonthNumber() {
        return monthNumber;
    }
}
