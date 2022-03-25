package com.example.football.service.impl;

import com.example.football.models.dto.RootStatImportDTO;
import com.example.football.models.dto.StatImportDTO;
import com.example.football.models.dto.TownImportDTO;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Town;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StatServiceImpl implements StatService {
    private final static Path PATH = Path.of("src", "main", "resources", "files", "xml", "stats.xml");

    private final StatRepository statRepository;
    private final ModelMapper mapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;


    @Autowired
    public StatServiceImpl(StatRepository statRepository, ModelMapper mapper, Validator validator) throws JAXBException {
        this.statRepository = statRepository;
        this.mapper = mapper;
        this.validator = validator;

        JAXBContext ctx = JAXBContext.newInstance(RootStatImportDTO.class);
        this.unmarshaller = ctx.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(PATH);
    }

    @Override
    public String importStats() throws FileNotFoundException, JAXBException {
        RootStatImportDTO dto = (RootStatImportDTO) this.unmarshaller
                .unmarshal(new FileReader(PATH.toString()));

        List<String> output = new ArrayList<>();

        dto.getStats()
                .forEach(s -> {
                    Set<ConstraintViolation<StatImportDTO>> err = validator.validate(s);

                    if (err.isEmpty()) {
                        Stat stat = this.mapper.map(s, Stat.class);
                        this.statRepository.save(stat);

                        output.add(String.format("Successfully imported Stat %.2f - %.2f - %.2f",
                                stat.getShooting(), stat.getPassing(), stat.getEndurance()));
                    } else {
                        output.add("Invalid Stat");
                    }

                    Optional<Stat> optStat =
                            this.statRepository
                                    .findByShootingAndPassingAndEndurance(s.getShooting(),
                                            s.getPassing(), s.getEndurance());

                    if (optStat.isPresent()) {
                        output.add("Invalid Stat");
                    }
                });
        return String.join("\n", output);
    }
}
