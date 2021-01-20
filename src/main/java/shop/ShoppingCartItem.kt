package shop

import java.math.BigDecimal

class ShoppingCartItem(private val product: Product, itemCost: Double,var quantity: Int) {
    private val itemCost: BigDecimal = BigDecimal.valueOf(itemCost)

    fun product(): Product {
        return product
    }

    fun itemCost(): BigDecimal {
        return itemCost
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val lineItem = other as ShoppingCartItem
        if (quantity != lineItem.quantity) return false
        return if (itemCost != lineItem.itemCost) false else product == lineItem.product
    }

    override fun hashCode(): Int {
        var result = itemCost.hashCode()
        result = 31 * result + product.hashCode()
        result = 31 * result + quantity
        return result
    }

}