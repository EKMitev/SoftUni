package com.example.football.service.impl;

import com.example.football.models.dto.PlayerImportDTO;
import com.example.football.models.dto.RootPlayerDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final static Path PATH = Path.of("src", "main", "resources", "files", "xml", "players.xml");

    private final PlayerRepository playerRepository;
    private final StatRepository statRepository;
    private final TownRepository townRepository;
    private final TeamRepository teamRepository;

    private final ModelMapper mapper;
    private final Validator validator;

    private final Unmarshaller unmarshaller;


    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, StatRepository statRepository, TownRepository townRepository, TeamRepository teamRepository, ModelMapper mapper, Validator validator) throws JAXBException {
        this.playerRepository = playerRepository;
        this.statRepository = statRepository;
        this.townRepository = townRepository;
        this.teamRepository = teamRepository;
        this.mapper = mapper;
        this.validator = validator;

        JAXBContext ctx = JAXBContext.newInstance(RootPlayerDTO.class);
        this.unmarshaller = ctx.createUnmarshaller();

        this.mapper.addConverter(context ->
                LocalDate.parse(context.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                String.class, LocalDate.class);
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(PATH);
    }

    @Override
    public String importPlayers() throws FileNotFoundException, JAXBException {
        RootPlayerDTO dtos = (RootPlayerDTO) this.unmarshaller
                .unmarshal(new FileReader(PATH.toString()));

        List<String> output = new ArrayList<>();

        dtos.getPlayers()
                .forEach(p -> {
                    Set<ConstraintViolation<PlayerImportDTO>> errors = validator.validate(p);

                    if(errors.isEmpty()){
                        Player player = this.mapper.map(p, Player.class);

                        Town town = this.townRepository.getByName(player.getTown().getName());
                        Team team = this.teamRepository.getByName(player.getTeam().getName());
                        Stat stat = this.statRepository.getById(player.getStat().getId());

                        player.setTown(town);
                        player.setTeam(team);
                        player.setStat(stat);

                        this.playerRepository.save(player);

                        output.add(String.format("Successfully imported Player %s %s - %s"
                        ,player.getFirstName(), player.getLastName(), player.getPosition().toString()));
                    } else {
                        output.add("Invalid Player");
                    }
                });

        return String.join("\n", output);
    }

    @Override
    public String exportBestPlayers() {
        LocalDate low = LocalDate.parse("01-01-1995", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate up = LocalDate.parse("01-01-2003", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        List<Player> bestPlayers = this.playerRepository.getByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastName(low, up);

        List<String> output = bestPlayers.stream()
                .map(Player::toString).toList();

        return String.join("\n", output);
    }
}
