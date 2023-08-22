package decorator

class DecoratorExample {
    fun example() {
        println("Decorator Example:")

        val beverage: Beverage = Espresso()
        println(beverage.description + " $ " + beverage.coast())
        val houseBled: Beverage = HouseBled()
        val hoseBlendWithMocha: Beverage = Mocha(houseBled)
        println(hoseBlendWithMocha.description + " $ " + hoseBlendWithMocha.coast())

        println("")
    }
}

abstract class Beverage(
    open val description: String = "Unknown Beverage",
) {
    abstract fun coast(): Double
}

abstract class CondimentDecorator : Beverage()

class Espresso(
    override val description: String = "Espresso"
) : Beverage() {
    override fun coast(): Double {
        return 1.99
    }
}

class HouseBled(
    override val description: String = "House Blend Coffee"
) : Beverage() {
    override fun coast(): Double {
        return 0.89
    }
}

class Mocha(
    private val beverage: Beverage,
): CondimentDecorator() {

    override fun coast(): Double {
        return .20 + beverage.coast()
    }

    override val description: String
        get() = beverage.description + ", Mocha"

}
