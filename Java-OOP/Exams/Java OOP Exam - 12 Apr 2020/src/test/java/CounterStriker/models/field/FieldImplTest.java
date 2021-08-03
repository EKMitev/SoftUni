package CounterStriker.models.field;

import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FieldImplTest {

    @Test
    public void t() {
        List<Player> players = new ArrayList<>();
        players.add(new Terrorist("t1", 10, 10, new Pistol("pistol", 11)));
        players.add(new Terrorist("t2", 10, 10, new Pistol("pistol", 11)));
        players.add(new Terrorist("t3", 10, 10, new Rifle("rifle", 31)));
        players.add(new CounterTerrorist("ct2", 10, 10, new Pistol("pistol", 11)));
        players.add(new CounterTerrorist("ct3", 10, 10, new Pistol("pistol", 11)));
        players.add(new CounterTerrorist("ct4", 10, 10, new Rifle("rifle", 311)));
        Field field = new FieldImpl();
        field.start(players);
    }

}