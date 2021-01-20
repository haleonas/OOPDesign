package shop

class ShoppingCartCaretaker {
    private lateinit var first: ShoppingCartMemento
    private lateinit var last: ShoppingCartMemento
    private lateinit var currentState: ShoppingCartMemento

    init{
        //creates an empty cart for if the client undoes the first item.
        this.addMemento(ShoppingCartMemento(ArrayList()))
    }

    fun addMemento(state: ShoppingCartMemento) {
        if(!this::first.isInitialized){
            this.first = state
            this.last = state
            this.currentState = state
        } else {
            if(currentState != last){
                //removes all the items after currentState
                currentState.next = null
                this.last = currentState
            }
            val temp = this.last
            temp.next = state
            state.prev = temp
            this.last = state
            currentState = state
        }
    }



    fun previousMemento(): ShoppingCartMemento{
        if(currentState != first){
            currentState = currentState.prev
        }

        return currentState
    }

    fun nextMemento(): ShoppingCartMemento {
        if(currentState != last){
            currentState = currentState.next!!
        }
        return currentState
    }
}