package com.example.football.service.impl;

import com.example.football.models.dto.TownImportDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final Validator validator;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, Gson gson, ModelMapper mapper, Validator validator) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "towns.json");

        return Files.readString(path);
    }

    @Override
    public String importTowns() throws IOException {
        String json = readTownsFileContent();

        TownImportDTO[] dtos = this.gson.fromJson(json, TownImportDTO[].class);

        List<String> output = new ArrayList<>();

        Arrays.stream(dtos).forEach(dto -> {
            Set<ConstraintViolation<TownImportDTO>> err = validator.validate(dto);

            if (this.townRepository.getByName(dto.getName()) != null) {
                output.add("Invalid Town");
            } else if (err.isEmpty()) {
                Town town = this.mapper.map(dto, Town.class);
                this.townRepository.save(town);

                output.add(String.format("Successfully imported Town %s - %d",
                        town.getName(), town.getPopulation()));
            } else {
                output.add("Invalid Town");
            }
        });
        return String.join("\n", output);
    }
}
