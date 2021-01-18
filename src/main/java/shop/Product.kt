package shop

import java.util.*

class Product(private val name: String) {
    private val id: UUID = UUID.randomUUID()

    fun name(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val product = other as Product
        return if (id != product.id) false else name == product.name
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}
