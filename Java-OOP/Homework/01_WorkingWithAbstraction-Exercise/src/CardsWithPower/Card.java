package CardsWithPower;

public class Card {

    private int power;

    public Card(CardSuit cardSuit, CardRank cardRank){
        this.power = cardSuit.getSuitPower() + cardRank.getPower();
    }

    public int getPower() {
        return power;
    }
}
