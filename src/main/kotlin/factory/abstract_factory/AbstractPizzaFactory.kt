package factory.abstract_factory


class AbstractFactoryExample() {
    fun example() {
        val nYPizzaStore: PizzaStore = NYPizzaStore()
        val chicagoStore: PizzaStore = ChicagoPizzaStore()

        var pizza = nYPizzaStore.orderPizza(PizzaType.CheesePizza)
        println("Client1 ordered a ${pizza.name} \n")

        pizza = chicagoStore.orderPizza(PizzaType.CheesePizza)
        println("Client2 ordered a ${pizza.name} \n")

        pizza = nYPizzaStore.orderPizza(PizzaType.Clam)
        println("Client3 ordered a ${pizza.name} \n")

        pizza = chicagoStore.orderPizza(PizzaType.Clam)
        println("Client4 ordered a ${pizza.name} \n")
    }
}

abstract class PizzaStore {

    fun orderPizza(type: PizzaType): Pizza {
        val pizza: Pizza = createPizza(type)

        pizza.prepare()
        pizza.bake()
        pizza.cut()
        pizza.box()
        return pizza
    }

    abstract fun createPizza(type: PizzaType): Pizza

}

class NYPizzaStore() : PizzaStore() {

    private val ingredientFactory: PizzaIngredientFactory = NYPizzaIngredientFactory()
    override fun createPizza(type: PizzaType): Pizza {
        return when (type) {
            PizzaType.CheesePizza -> {
                CheesePizza(ingredientFactory)
            }

            PizzaType.Clam -> NYStyleClamPizza(ingredientFactory =  ingredientFactory)
        }
    }

}

class ChicagoPizzaStore() : PizzaStore() {

    private val ingredientFactory: PizzaIngredientFactory = ChicagoIngredientFactory()
    override fun createPizza(type: PizzaType): Pizza {
        return when (type) {
            PizzaType.CheesePizza -> ChicagoStyleCheesePizza(ingredientFactory= ingredientFactory)
            PizzaType.Clam -> ChicagoStyleClamPizza()
        }
    }
}

class CheesePizza(
    private val ingredientFactory: PizzaIngredientFactory
) : Pizza() {

    override fun prepare() {
        println("Preparing  $name")
        dough = ingredientFactory.createDough()
        sauce = ingredientFactory.createSauce()
    }
}

class NYStyleCheesePizza(
    override val name: String = "NY Style Sauce and Dough Pizza",
) : Pizza(name) {
    override fun prepare() {

    }
}

class NYStyleClamPizza(
    override val name: String = "NYStyleClamPizza",
    private val ingredientFactory: PizzaIngredientFactory
) : Pizza(name) {

    override fun prepare() {

    }
}

class ChicagoStyleCheesePizza(
    override val name: String = "Chicago Style Deep Dish Cheese Pizza",
    private val ingredientFactory: PizzaIngredientFactory
) : Pizza(name) {

    override fun prepare() {
        println("Preparing  $name")
        dough = ingredientFactory.createDough()
        sauce = ingredientFactory.createSauce()
    }

    override fun cut() {
        println("Cutting the pizza into square slices")
    }
}

class ChicagoStyleClamPizza(
    override val name: String = "Chicago Style Clam Pizza",
) : Pizza(name) {
    override fun prepare() {

    }
}


abstract class Pizza(
    open val name: String = "",
    open var dough: Dough? = null,
    open var sauce: Sauce? = null,
    open val toppings: List<String> = emptyList()
) {
    abstract fun prepare()

    fun bake() {
        println("Bake for 25 minutes at 350")
    }

    open fun cut() {
        println("Cutting the pizza into diagonal slices”")
    }

    fun box() {
        println("“Place pizza in official PizzaStore box")
    }
}

sealed class PizzaType() {
    object CheesePizza : PizzaType()
    object Clam : PizzaType()
}