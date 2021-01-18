package shop

import java.math.BigDecimal

interface DiscountStrategy {
    fun calcDiscount(items: ArrayList<ShoppingCartItem>): BigDecimal
}