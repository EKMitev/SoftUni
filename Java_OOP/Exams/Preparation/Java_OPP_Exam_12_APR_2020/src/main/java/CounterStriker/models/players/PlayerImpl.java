package CounterStriker.models.players;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.guns.Gun;

public abstract class PlayerImpl implements Player {

    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    public PlayerImpl(String username, int health, int armor, Gun gun) {
        setUsername(username);
        setHealth(health);
        setArmor(armor);
        this.isAlive = isAlive();
        setGun(gun);
    }


    @Override
    public String getUsername() {
        return this.username;
    }

    private void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    protected void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    protected void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    @Override
    public Gun getGun() {
        return this.gun;
    }

    private void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN);
        }
        this.gun = gun;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public void takeDamage(int points) {

        int currArmor = this.getArmor() - points;

        if (currArmor > 0) {
            this.armor -= points;
        } else {
            this.armor = 0;
            this.health -= Math.abs(currArmor);
        }

        if (this.health < 0){
            this.health = 0;
        }

        this.isAlive = isAlive();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName()).append(": ").append(this.getUsername())
                .append(System.lineSeparator())
                .append("--Health: ").append(this.getHealth())
                .append(System.lineSeparator())
                .append("--Armor: ").append(this.getArmor())
                .append(System.lineSeparator())
                .append("--Gun: ").append(this.getGun().getName())
                .append(System.lineSeparator());

        return sb.toString().trim();
    }
}
