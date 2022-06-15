package com.example.demo.models.mapper;


import com.example.demo.models.dto.AddOfferDTO;
import com.example.demo.models.entity.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    Offer mapDtoToOffer(AddOfferDTO offerDTO);
}
