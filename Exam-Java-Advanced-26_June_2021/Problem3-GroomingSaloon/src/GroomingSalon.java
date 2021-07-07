import java.util.ArrayList;
import java.util.List;

public class GroomingSalon {
    private final int capacity;
    private final List<Pet> data;

    public GroomingSalon(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Pet pet) {
        if (this.data.size() < capacity) {
            this.data.add(pet);
        }
    }

    public boolean remove(String name) {
        Pet pet = this.data.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (pet != null) {
            this.data.remove(pet);
            return true;
        }
        return false;
    }

    public Pet getPet(String name, String owner) {
        return this.data.stream()
                .filter(p -> p.getName().equals(name) && p.getOwner().equals(owner))
                .limit(1)
                .findFirst()
                .orElse(null);
    }

    public int getCount(){
        return this.data.size();
    }

    public String getStatistics(){
        StringBuilder builder = new StringBuilder("The grooming salon has the following clients:").append(System.lineSeparator());
        this.data
                .forEach(p -> builder.append(p.getName()).append(" ").append(p.getOwner()).append(System.lineSeparator()));
        return " " + builder.toString().trim();
    }
}
