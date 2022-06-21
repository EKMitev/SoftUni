package bg.softuni.battleships.model.DTO;

public class FireModel {
    private long attackerId;
    private long defenderId;

    public long getAttackerId() {
        return attackerId;
    }

    public FireModel setAttackerId(long attackerId) {
        this.attackerId = attackerId;
        return this;
    }

    public long getDefenderId() {
        return defenderId;
    }

    public FireModel setDefenderId(long defenderId) {
        this.defenderId = defenderId;
        return this;
    }
}
