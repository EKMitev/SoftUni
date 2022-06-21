package bg.softuni.battleships.model.mapper;

import bg.softuni.battleships.model.DTO.AddShipDTO;
import bg.softuni.battleships.model.DTO.ShipDTO;
import bg.softuni.battleships.model.entity.Ship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ShipMapper {

   public Ship mapFromAddShipDto(AddShipDTO addShipDTO) {

        return new Ship()
                .setName(addShipDTO.getName())
                .setPower(addShipDTO.getPower())
                .setHealth(addShipDTO.getHealth())
                .setCreated(addShipDTO.getCreated());
    }

    abstract public ShipDTO mapFromEntity(Ship ship);
}
