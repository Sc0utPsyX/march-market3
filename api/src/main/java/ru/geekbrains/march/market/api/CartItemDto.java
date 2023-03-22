package ru.geekbrains.march.market.api;

import java.math.BigDecimal;

public class CartItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    private CartItemDto(Builder builder) {
        setProductId(builder.productId);
        setProductTitle(builder.productTitle);
        setQuantity(builder.quantity);
        setPricePerProduct(builder.pricePerProduct);
        setPrice(builder.price);
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CartItemDto() {
    }


    public static final class Builder {
        private Long productId;
        private String productTitle;
        private int quantity;
        private BigDecimal pricePerProduct;
        private BigDecimal price;

        public Builder() {
        }

        public Builder withProductId(Long val) {
            productId = val;
            return this;
        }

        public Builder withProductTitle(String val) {
            productTitle = val;
            return this;
        }

        public Builder withQuantity(int val) {
            quantity = val;
            return this;
        }

        public Builder withPricePerProduct(BigDecimal val) {
            pricePerProduct = val;
            return this;
        }

        public Builder withPrice(BigDecimal val) {
            price = val;
            return this;
        }

        public CartItemDto build() {
            return new CartItemDto(this);
        }
    }
}
