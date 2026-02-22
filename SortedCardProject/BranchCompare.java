//package Serge.Card.File.Reader;

import java.util.Comparator;

public class BranchCompare implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        return o2.issuerReference-o1.issuerReference;
    }
}
