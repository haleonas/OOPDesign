package shop

class ShoppingCartMemento(_items: ArrayList<ShoppingCartItem>) {
    val items: ArrayList<ShoppingCartItem> = ArrayList(_items)
    var next: ShoppingCartMemento? = null
    lateinit var prev: ShoppingCartMemento
}