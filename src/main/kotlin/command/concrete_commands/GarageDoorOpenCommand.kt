package command.concrete_commands

import command.Command
import command.recievers.GarageDoor

class GarageDoorOpenCommand(
    private val garageDoor: GarageDoor
): Command {
    override fun execute() {
        garageDoor.open()
    }

    override fun undo() {
        garageDoor.close()
    }
}

class GarageDoorCloseCommand(
    private val garageDoor: GarageDoor
): Command {
    override fun execute() {
        garageDoor.close()
    }

    override fun undo() {
        garageDoor.open()
    }
}