package command

interface Command {
    fun execute()

    fun undo()
}

class MacroCommand(
    private val commands: List<Command>
) : Command {

    override fun execute() {
        commands.forEach {
            it.execute()
        }
    }

    override fun undo() {
        commands.forEach {
            it.undo()
        }
    }

}

class NoCommand : Command {
    override fun execute() {
        println("No Command")
    }

    override fun undo() {
        println("No Command")
    }
}