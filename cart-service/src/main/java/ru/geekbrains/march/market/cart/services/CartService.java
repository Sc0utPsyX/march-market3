package ru.geekbrains.march.market.cart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.march.market.cart.utils.Cart;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;

    public Cart getCurrentCart(String cartId) {
        if (!redisTemplate.hasKey(cartId)) {
            Cart cart = new Cart();
            redisTemplate.opsForValue().set(cartId, cart);
        }
        return (Cart)redisTemplate.opsForValue().get(cartId);
    }

    public void mergeCarts(String guestCartId, String userNameCartId){
        if (!redisTemplate.hasKey(guestCartId)){
            return;
        }
        Cart guestCart = (Cart) redisTemplate.opsForValue().get(guestCartId);
        if (!redisTemplate.hasKey(userNameCartId)){
            redisTemplate.opsForValue().set(userNameCartId, guestCart);
            return;
        }
        guestCart.getItems().forEach(cartItem -> {
            addToCart(userNameCartId, cartItem.getProductId(), cartItem.getQuantity());
        });
        clearCart(guestCartId);
    }

    public void addToCart(String cartId, Long productId) {
        execute(cartId, cart -> {
            ProductDto p = productServiceIntegration.findById(productId);
            cart.add(p);
        });
    }

    public void addToCart(String cartId, Long productId, int quantity) {
        execute(cartId, cart -> {
            ProductDto p = productServiceIntegration.findById(productId);
            for (int i = 0; i < quantity; i++) {
                cart.add(p);
            }
        });
    }

    public void removeFromCart(String cartId, Long productId) {
        execute(cartId, cart -> cart.remove(productId));
    }

    public void clearCart(String cartId) {
        execute(cartId, Cart::clear);
    }

    private void execute(String cartId, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartId);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartId, cart);
    }
}
