package composite

abstract class MenuComponent {
    open fun getName(): String {
        throw UnsupportedOperationException()
    }

    open fun getDescription(): String {
        throw UnsupportedOperationException()
    }

    open fun getPrice(): Double {
        throw UnsupportedOperationException()
    }

    open fun isVegetarian(): Boolean {
        throw UnsupportedOperationException()
    }

    open fun print() {
        throw UnsupportedOperationException()
    }

    open fun add(menuComponent: MenuComponent) {
        throw UnsupportedOperationException()
    }

    open fun remove(menuComponent: MenuComponent) {
        throw UnsupportedOperationException()
    }

    open fun getChild(i: Int): MenuComponent {
        throw UnsupportedOperationException()
    }
}

class MenuItem(
    private val name: String,
    private val description: String,
    private val vegetarian: Boolean,
    private val price: Double
) : MenuComponent() {
    override fun getName() = name
    override fun getDescription() = description
    override fun getPrice() = price

    override fun isVegetarian() = vegetarian

    override fun print() {
        println(" $name")
        if (isVegetarian()) {
            print("(v)")
        }
        println(", $price")
        println("--- $description")
    }
}

class Menu(
    private val name: String,
    private val description: String
) : MenuComponent() {

    private val menuComponents: MutableList<MenuComponent> = mutableListOf()
    override fun getName(): String = name
    override fun getDescription(): String = description

    override fun remove(menuComponent: MenuComponent) {
        menuComponent.remove(menuComponent)
    }

    override fun add(menuComponent: MenuComponent) {
        menuComponents.add(menuComponent)
    }

    override fun getChild(i: Int): MenuComponent {
        return menuComponents[i]
    }

    override fun print() {
        print("\n" + getName())
        println(", " + getDescription())
        println("---------------------")

        val iterator = menuComponents.iterator()
        while (iterator.hasNext()) {
            val menuComponent = iterator.next()
            menuComponent.print()
        }
    }
}