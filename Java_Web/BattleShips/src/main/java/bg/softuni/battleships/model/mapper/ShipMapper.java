package bg.softuni.battleships.model.mapper;

import bg.softuni.battleships.model.DTO.ShipDTO;
import bg.softuni.battleships.model.entity.Category;
import bg.softuni.battleships.model.entity.CategoryEnum;
import bg.softuni.battleships.model.entity.Ship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ShipMapper {

   public Ship mapFromShipDto(ShipDTO shipDTO) {

        return new Ship()
                .setName(shipDTO.getName())
                .setPower(shipDTO.getPower())
                .setHealth(shipDTO.getHealth())
                .setCreated(shipDTO.getCreated());
    }
}
