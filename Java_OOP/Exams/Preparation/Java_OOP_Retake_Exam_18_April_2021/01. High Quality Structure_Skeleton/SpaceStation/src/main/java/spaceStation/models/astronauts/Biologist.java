package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {

    private static final double INITIAL_OXYGEN = 70;

    public Biologist(String name) {
        super(name, INITIAL_OXYGEN);
    }

    @Override
    public boolean canBreath() {
        return getOxygen() >= 5;
    }

    @Override
    public void breath() {
        if (this.canBreath()) {
            setOxygen(this.getOxygen() - 5);
        }
    }
}
