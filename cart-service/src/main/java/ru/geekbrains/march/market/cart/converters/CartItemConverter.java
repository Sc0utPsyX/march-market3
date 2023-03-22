package ru.geekbrains.march.market.cart.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.cart.utils.CartItem;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem c) {
        return new CartItemDto.Builder()
                .withProductId(c.getProductId())
                .withProductTitle(c.getProductTitle())
                .withPrice(c.getPrice())
                .withPricePerProduct(c.getPricePerProduct())
                .withQuantity(c.getQuantity())
                .build();
    }
}
