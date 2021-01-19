package shop

fun main() {

    val item1 = Product("Deodorant")
    val item2 = Product("Pepsi")
    val item3 = Product("Ketchup")

    val shoppingCartItem1 = ShoppingCartItem(item1, 10.0, 100)
    val shoppingCartItem2 = ShoppingCartItem(item2, 15.0, 5)
    val shoppingCartItem3 = ShoppingCartItem(item3, 20.0, 5)

    val shoppingCart = ShoppingCart()
    val caretaker = ShoppingCartCaretaker()

    caretaker.addMemento(shoppingCart.addCartItem(shoppingCartItem1))
    shoppingCart.undo(caretaker.previousMemento())//- 4:e
    caretaker.addMemento(shoppingCart.addCartItem(shoppingCartItem2))
    caretaker.addMemento(shoppingCart.addCartItem(shoppingCartItem3))
    caretaker.addMemento(shoppingCart.addCartItem(shoppingCartItem2))//+ 4:E
    shoppingCart.undo(caretaker.previousMemento())//- 4:e
    caretaker.addMemento(shoppingCart.addCartItem(shoppingCartItem1))//+ 4:e tar bort den gamla 4:e
    shoppingCart.undo(caretaker.previousMemento())//- 4:e nu 3
    shoppingCart.undo(caretaker.previousMemento())//- 3:e nu 2
    shoppingCart.redo(caretaker.nextMemento())// tillbaka till 3 items
    shoppingCart.redo(caretaker.nextMemento())// tillbaka till 4 items

    println(shoppingCart.receipt())

}

