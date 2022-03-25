package com.example.football.service.impl;

import com.example.football.models.dto.TeamImportDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final Validator validator;
    private final TownRepository townRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, Gson gson, ModelMapper mapper, Validator validator, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validator = validator;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "teams.json");

        return Files.readString(path);
    }

    @Override
    public String importTeams() throws IOException {
        String json = readTeamsFileContent();

        TeamImportDTO[] dtos = this.gson.fromJson(json, TeamImportDTO[].class);

        List<String> output = new ArrayList<>();

        Arrays.stream(dtos).forEach(dto -> {
            Set<ConstraintViolation<TeamImportDTO>> err = validator.validate(dto);

            if (err.isEmpty()) {
                Team team = this.mapper.map(dto, Team.class);
                Town town = this.townRepository.getByName(dto.getTownName());

                team.setTown(town);
                this.teamRepository.save(team);

                output.add(String.format("Successfully imported Team %s - %d",
                        team.getName(), team.getFanBase()));
            } else {
                output.add("Invalid Team");
            }
        });
        return String.join("\n", output);
    }
}
