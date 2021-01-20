package shop

import java.math.BigDecimal

class DiscountAll : DiscountStrategy {
    override fun calcDiscount(items: ArrayList<ShoppingCartItem>): BigDecimal {
        var sum = BigDecimal.ZERO
        items.map { sum += (it.itemCost() * BigDecimal(it.quantity))}
        return sum * BigDecimal(0.1) //10% discount
    }
}