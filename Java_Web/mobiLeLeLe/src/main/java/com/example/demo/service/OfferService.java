package com.example.demo.service;

import com.example.demo.models.dto.AddOfferDTO;
import com.example.demo.models.dto.OfferDTO;
import com.example.demo.models.entity.Model;
import com.example.demo.models.entity.Offer;
import com.example.demo.models.entity.User;
import com.example.demo.models.mapper.OfferMapper;
import com.example.demo.repository.ModelRepository;
import com.example.demo.repository.OfferRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class OfferService {

    private final OfferMapper offerMapper;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;

    public OfferService(OfferMapper offerMapper, UserRepository userRepository, ModelRepository modelRepository, OfferRepository offerRepository) {
        this.offerMapper = offerMapper;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
    }

    public void addOffer(AddOfferDTO offerDTO, Principal principal) {
        Offer offer = this.offerMapper.mapDtoToOffer(offerDTO);

        User seller = this.userRepository.findByUsername(principal.getName())
                .orElseThrow();


        Model model = modelRepository.findById(offerDTO.getModelId())
                .orElseThrow();

        offer
                .setSeller(seller)
                .setModel(model);

        this.offerRepository.save(offer);
    }

    public List<OfferDTO> getAllOffers() {
        return this.offerRepository.findAll()
                .stream()
                .map(this.offerMapper::mapFromEntity)
                .toList();
    }
}
