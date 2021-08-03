package CounterStriker.models.guns;

public abstract class GunImpl implements Gun {

    private String name;
    private int bulletsCount;

    public GunImpl(String name, int bulletsCount) {
        setName(name);
        setBulletsCount(bulletsCount);
    }

    private void setName(String name){
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException("Gun cannot be null or empty.");
        }
        this.name = name;
    }

    protected void setBulletsCount(int bulletsCount) {
        if (bulletsCount < 0){
            throw new IllegalArgumentException("Bullets cannot be below 0.");
        }
        this.bulletsCount = bulletsCount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsCount() {
        return this.bulletsCount;
    }

}
