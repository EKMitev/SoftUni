package com.example.demo.models.mapper;


import com.example.demo.models.dto.AddOfferDTO;
import com.example.demo.models.dto.OfferDTO;
import com.example.demo.models.entity.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    Offer mapDtoToOffer(AddOfferDTO offerDTO);

    @Mapping(source = "model.name", target = "model")
    @Mapping(source = "model.brand.name", target = "brand")
    OfferDTO mapFromEntity(Offer offer);
}
