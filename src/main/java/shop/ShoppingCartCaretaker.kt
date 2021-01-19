package shop

class ShoppingCartCaretaker {
    private val mementoList = ArrayList<ShoppingCartMemento>()
    private lateinit var lastAdded: ShoppingCartMemento
    private lateinit var currentState: ShoppingCartMemento

    init{
        this.addMemento(ShoppingCartMemento(ArrayList()))
    }

    fun addMemento(state: ShoppingCartMemento) {
        //if we are in middle of list remove all previous states and add new, only if current state is Initialized
        lastAdded = state
        if (this::currentState.isInitialized) {
            val index = mementoList.indexOf(currentState)
            println(mementoList.size)
            for (i in mementoList.size - 1 downTo index + 1) {
                println(i)
                mementoList.removeAt(i)
            }
            currentState = lastAdded
        }
        mementoList.add(state)

    }


    fun getSpecificMemento(index: Int): ShoppingCartMemento {
        return mementoList[index]
    }

    fun previousMemento(): ShoppingCartMemento{
        if (!this::currentState.isInitialized)
            currentState = lastAdded

        val index = mementoList.indexOf(currentState)
        if (index > 0) {
            currentState = mementoList[index - 1]
        }

        return currentState
    }

    fun nextMemento(): ShoppingCartMemento {
        val index = mementoList.indexOf(currentState)
        if (index + 1 < mementoList.size) {
            currentState = mementoList[index + 1]
        }
        return currentState
    }
}