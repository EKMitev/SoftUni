package com.example.demo.service;

import com.example.demo.CurrentUser;
import com.example.demo.models.dto.AddOfferDTO;
import com.example.demo.models.entity.Model;
import com.example.demo.models.entity.Offer;
import com.example.demo.models.entity.User;
import com.example.demo.models.mapper.OfferMapper;
import com.example.demo.repository.ModelRepository;
import com.example.demo.repository.OfferRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    private final OfferMapper offerMapper;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;

    public OfferService(OfferMapper offerMapper, UserRepository userRepository, CurrentUser currentUser, ModelRepository modelRepository, OfferRepository offerRepository) {
        this.offerMapper = offerMapper;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
    }

    public void addOffer(AddOfferDTO offerDTO) {
        Offer offer = this.offerMapper.mapDtoToOffer(offerDTO);

        //FIXME: user should be logged in
        System.out.println(this.currentUser);

        User seller = this.userRepository.findByUsername(currentUser.getUserName())
                .orElseThrow();

        Model model = modelRepository.findById(offerDTO.getModelId())
                .orElseThrow();

        offer
                .setSeller(seller)
                .setModel(model);

        this.offerRepository.save(offer);
    }
}
