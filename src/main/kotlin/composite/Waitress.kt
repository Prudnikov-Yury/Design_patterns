package composite

class Waitress(
    private val allMenus: MenuComponent
) {
    fun printMenu() {
        allMenus.print()
    }

}

class MenuTestDrive() {
    fun test() {
        val pancakeHouseMenu: MenuComponent = Menu(name = "PANCAKE HOUSE MENU", "Breakfast")
        val dinerMenu: MenuComponent = Menu(name = "DINER MENU", "Lunch")
        val cafeMenu: MenuComponent = Menu(name = "CAFE MENU", "Dinner")
        val dessertMenu: MenuComponent = Menu(name = "DESSERT MENU", "Dessert of course!")

        val allMenus: MenuComponent = Menu("ALL MENUS", "All menus combined")

        allMenus.add(pancakeHouseMenu)
        allMenus.add(dinerMenu)
        allMenus.add(cafeMenu)

        dinerMenu.add(
            MenuItem(
                name = "Pasta",
                description = "Spaghetti with Marinara Sauce, and a slice of sourdough bread",
                vegetarian = true,
                price = 3.89
            )
        )

        dinerMenu.add(dessertMenu)

        dessertMenu.add(
            MenuItem(
                name = "Apple Pie",
                description = "Apple pie with a flakey crust, topped with vanilla icecream",
                vegetarian = true,
                price = 1.89
            )
        )

        val waitress = Waitress(allMenus)
        waitress.printMenu()
    }
}