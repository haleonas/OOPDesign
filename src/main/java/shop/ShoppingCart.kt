package shop

import java.math.BigDecimal
import java.util.Comparator
import java.util.stream.Collectors
import java.util.stream.Stream

class ShoppingCart {
    private var items = ArrayList<ShoppingCartItem>()
    private var discount: DiscountStrategy? = null

    fun addCartItem(item: ShoppingCartItem):ShoppingCartMemento {
        items.add(item)
        return ShoppingCartMemento(items)
    }

    fun stream(): Stream<ShoppingCartItem> {
        return items.stream()
    }

    private fun calculatePrice(): BigDecimal {
        var sum = BigDecimal.ZERO
        val discountDirector = DiscountDirector()
        discount = discountDirector.countDiscount(items)

        for (item in items) {
            sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity.toLong())).add(sum)
        }

        return if (discount != null) {
            sum - discount!!.calcDiscount(items)
        } else
            sum
    }

    fun changeAmount(amount:Int, index:Int):ShoppingCartMemento{
        items[index].quantity = amount
        return ShoppingCartMemento(items)
    }

    fun undo(memento: ShoppingCartMemento) {
        items = ArrayList(memento.items)
    }

    fun redo(memento: ShoppingCartMemento) {
        items = ArrayList(memento.items)
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
        for (each in list) {
            sb.append(String.format("%-4s %-10s % 7.2f\n", each.quantity,each.product().name(), each.itemCost()))
        }
        sb.append(line)
        sb.append(String.format("%24s% 8.2f", "TOTAL:", calculatePrice()))
        if(discount != null){
            sb.append(String.format("\n%24s% 8.2f", "Discount:", discount?.calcDiscount(items)))
        }

        return sb.toString()
    }
}
