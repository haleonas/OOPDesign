package shop

import java.math.BigDecimal

class DiscountDirector {
    fun countDiscount(items: ArrayList<ShoppingCartItem>): DiscountStrategy? {
        val (sum, totalWares) = calcItems(items)

        val discounts = createDiscountList(items, sum, totalWares)

        return if (discounts.isNotEmpty()) {
            calcBestStrategy(discounts,items)
        } else
            null
    }

    private fun calcItems(items: ArrayList<ShoppingCartItem>): Pair<BigDecimal, Int> {
        var sum = BigDecimal.ZERO
        var totalWares = 0

        items.map {
            sum += it.itemCost() * BigDecimal(it.quantity())
            totalWares += it.quantity()
        }
        return Pair(sum, totalWares)
    }

    private fun createDiscountList(
        items: ArrayList<ShoppingCartItem>,
        sum: BigDecimal,
        totalWares: Int,
    ): ArrayList<DiscountStrategy> {
        val discounts = ArrayList<DiscountStrategy>()

        if (items.size > 10)
            discounts.add(DiscountItemFree())

        if (sum > BigDecimal(500))
            discounts.add(DiscountAll())

        if (totalWares == 3)
            discounts.add(Discount3for2())

        return discounts
    }

    private fun calcBestStrategy(discounts: ArrayList<DiscountStrategy>,items: ArrayList<ShoppingCartItem>): DiscountStrategy {
        var biggestDiscount = discounts[0]
        discounts.map {
            if (biggestDiscount.calcDiscount(items) < it.calcDiscount(items)) {
                biggestDiscount = it
            }
        }
        return biggestDiscount
    }
}

