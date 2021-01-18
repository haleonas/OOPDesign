package shop

import java.math.BigDecimal

class Discount3for2 : DiscountStrategy {
    override fun calcDiscount(items: ArrayList<ShoppingCartItem>): BigDecimal {
        var cheapestItem = items[0]
        items.map {
            if (cheapestItem.itemCost() > it.itemCost()) {
                cheapestItem = it
            }
        }
        return cheapestItem.itemCost()
    }
}