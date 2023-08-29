package command.invokers

import command.Command
import command.NoCommand

//Класс RemoteControl управляет набором
//объектов команд (по одному на кнопку). При
//нажатии кнопки вызывается соответствующий
//метод ButtonWasPushed(), который
//активизирует метод execute() объекта
//команды. Класс пульта больше ничего не
//знает о тех классах, к которым он обращается,
//так как он отделен от них объектом команды

open class RemoteControl(private val recieversCount: Int) {
    protected val onCommands = mutableListOf<Command>()
    protected val offCommands = mutableListOf<Command>()

    init {
        val noCommand: Command = NoCommand()
        repeat(recieversCount) {
            onCommands.add(index = it, noCommand)
            offCommands.add(index = it, noCommand)
        }
    }

    fun setCommand(slot: Int, onCommand: Command, offCommand: Command) {
        onCommands[slot] = onCommand
        offCommands[slot] = offCommand
    }

    open fun onButtonWasPushed(slot: Int) {
        onCommands[slot].execute()
    }

    open fun offButtonWasPushed(slot: Int) {
        offCommands[slot].execute()
    }
}

class RemoteControlWithUndo(
    recieversCount: Int,
) : RemoteControl(recieversCount) {
    private var undoCommand: Command = NoCommand()

    override fun onButtonWasPushed(slot: Int) {
        super.onButtonWasPushed(slot)
        undoCommand  = onCommands[slot]
    }

    override fun offButtonWasPushed(slot: Int) {
        super.offButtonWasPushed(slot)
        undoCommand = offCommands[slot]
    }

    fun undoButtonWasPushed() {
        undoCommand.undo()
    }
}
