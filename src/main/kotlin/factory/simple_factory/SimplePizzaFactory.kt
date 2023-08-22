package factory.simple_factory


class SimpleFactoryExample() {
    fun example() {
        val pizzaFactory = SimplePizzaFactory()
        val cheesePizza = PizzaType.CheesePizza
        val store = PizzaStore(pizzaFactory)
        store.orderPizza(cheesePizza)
    }
}

open class PizzaStore(private val pizzaFactory: SimplePizzaFactory) {

    fun orderPizza(type: PizzaType): Pizza {
        val pizza: Pizza = pizzaFactory.createPizza(type)

        pizza.prepare()
        pizza.bake()
        pizza.cut()
        pizza.box()
        return pizza
    }

}

class SimplePizzaFactory {
    fun createPizza(type: PizzaType): Pizza {
        return when (type) {
            PizzaType.CheesePizza -> CheesePizza()
            PizzaType.Clam -> ClamPizza()
        }
    }
}

interface Pizza {
    fun prepare()
    fun bake()
    fun cut()
    fun box()

}

class VeggiePizza : Pizza {
    override fun prepare() {
        println("prepare: VeggiePizza")
    }

    override fun bake() {
        println("bake: VeggiePizza")
    }

    override fun cut() {
        println("cut: VeggiePizza")
    }

    override fun box() {
        println("box: VeggiePizza")
    }

}

class ClamPizza : Pizza {
    override fun prepare() {
        println("prepare: Clam")
    }

    override fun bake() {
        println("bake: Clam")
    }

    override fun cut() {
        println("cut: Clam")
    }

    override fun box() {
        println("box: Clam")
    }
}

class CheesePizza : Pizza {
    override fun prepare() {
        println("prepare: Cheese")
    }

    override fun bake() {
        println("bake: Cheese")
    }

    override fun cut() {
        println("cut: Cheese")
    }

    override fun box() {
        println("box: Cheese")
    }
}

sealed class PizzaType() {
    object CheesePizza : PizzaType()
    object Clam : PizzaType()
}