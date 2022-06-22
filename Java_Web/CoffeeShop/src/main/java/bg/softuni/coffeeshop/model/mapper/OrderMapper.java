package bg.softuni.coffeeshop.model.mapper;

import bg.softuni.coffeeshop.model.dto.OrderAddDTO;
import bg.softuni.coffeeshop.model.dto.OrderDTO;
import bg.softuni.coffeeshop.model.entity.Order;
import bg.softuni.coffeeshop.model.entity.OrderType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    public Order mapFromOrderAddDto(OrderAddDTO orderAddDTO) {
        return new Order()
                .setName(orderAddDTO.getName())
                .setPrice(orderAddDTO.getPrice())
                .setOrderTime(orderAddDTO.getOrderTime())
                .setDescription(orderAddDTO.getDescription());
    };

    public abstract OrderDTO mapFromEntity(Order order);
}
