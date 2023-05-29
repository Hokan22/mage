package mage.sets;

import mage.cards.ExpansionSet;
import mage.constants.SetType;

public final class Maggi extends ExpansionSet {

    private static final Maggi instance = new Maggi();

    public static Maggi getInstance() {
        return instance;
    }

    private Maggi() {
        super("Maggi", "MAG", ExpansionSet.buildDate(2023, 5, 29), SetType.CUSTOM_SET);
        this.blockName = "Maggi";
        this.hasBasicLands = false;
        this.hasBoosters = false;
    }
}
