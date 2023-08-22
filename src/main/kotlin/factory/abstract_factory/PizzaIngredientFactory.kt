package factory.abstract_factory

interface PizzaIngredientFactory {
    fun createSauce(): Sauce
    fun createCheese(): Cheese
    fun createDough(): Dough
}


class NYPizzaIngredientFactory : PizzaIngredientFactory {
    override fun createSauce(): Sauce {
        return NYSauce("NYSauce")
    }

    override fun createCheese(): Cheese {
        return NYCheese("NYCheese")
    }

    override fun createDough(): Dough {
       return NYDough("NYDough")
    }

}

class ChicagoIngredientFactory : PizzaIngredientFactory {
    override fun createSauce(): Sauce {
        return NYSauce(" ChicagoSauce")
    }

    override fun createCheese(): Cheese {
        return NYCheese(" ChicagoCheese")
    }

    override fun createDough(): Dough {
        return NYDough(" ChicagoDough")
    }

}





class NYSauce(
    val name: String
) : Sauce
class NYCheese(
    val name: String
) : Cheese

class NYDough(
    val name: String
) : Dough

class  ChicagoSauce(
    val name: String
) : Sauce
class  ChicagoCheese(
    val name: String
) : Cheese

class  ChicagoDough(
    val name: String
) : Dough

interface Sauce
interface Dough
interface Cheese

