package ru.geekbrains.march.market.core.observers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.core.integrations.CartServiceIntegration;

@Component
@AllArgsConstructor
public class CartItemsObserver implements Observer{
    private final CartServiceIntegration cartServiceIntegration;

    @Override
    public void update(Long productId) {
        cartServiceIntegration.removeProductFromCart(productId);
    }
}
