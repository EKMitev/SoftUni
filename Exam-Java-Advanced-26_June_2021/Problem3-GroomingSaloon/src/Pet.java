public class Pet {
    private final String name;
    private final int age;
    private final String owner;

    public Pet(String name, int age, String owner) {
        this.name = name;
        this.age = age;
       this.owner = owner;
    }

    public String getName() {
        return name;
    }



    public String getOwner() {
        return owner;
    }


    @Override
    public String toString() {
        return String.format("%s %d - (%s)", this.name, this.age, this.owner);
    }
}
