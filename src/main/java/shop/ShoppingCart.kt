package shop

import java.math.BigDecimal
import java.util.Comparator
import java.util.stream.Collectors

class ShoppingCart {
    private var items = ArrayList<ShoppingCartItem>()
    private var discount: DiscountStrategy? = null
    private lateinit var totalDiscount: BigDecimal

    fun addCartItem(item: ShoppingCartItem): ShoppingCartMemento {
        items.add(item)
        return ShoppingCartMemento(items)
    }

    private fun calculatePrice(): BigDecimal {
        var sum = BigDecimal.ZERO
        val discountDirector = DiscountDirector()
        discount = discountDirector.countDiscount(items)

        for (item in items)
            sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity().toLong())).add(sum)

        return if (discount != null){
            totalDiscount = discount!!.calcDiscount(items)
            sum - totalDiscount
        }
        else
            sum
    }

    fun receipt(): String {
        val line = "--------------------------------\n"
        val sb = StringBuilder()
        sb.append(line)
        val list = items.stream()
            .sorted(Comparator.comparing { item: ShoppingCartItem ->
                item.product().name()
            })
            .collect(Collectors.toList())
        list.forEach { cartItem ->
            sb.append(String.format("%-4s %-10s % 7.2f\n", cartItem.quantity(), cartItem.product().name(), cartItem.itemCost()))
        }
        sb.append(line)
        sb.append(String.format("%24s% 8.2f", "TOTAL:", calculatePrice()))

        if (discount == null) return sb.toString()
        sb.append(String.format("\n%24s% 8.2f", "Discount:", totalDiscount))

        return sb.toString()
    }

    fun undo(memento: ShoppingCartMemento) {
        items = ArrayList(memento.items)
    }

    fun redo(memento: ShoppingCartMemento) {
        items = ArrayList(memento.items)
    }
}
