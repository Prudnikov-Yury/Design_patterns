package command

import command.concrete_commands.GarageDoorOpenCommand
import command.concrete_commands.LightOnCommand
import command.invokers.SimpleRemoteControl
import command.recievers.GarageDoor
import command.recievers.Light


//Клиент в терминологии паттерна Команда (Client) - Отвечает за создание ConcreteCommand и назначение Receiver
class RemoteControlTest() {
    fun test() {
        val remote = SimpleRemoteControl() //Инициатор  (Invoker)

        val light = Light("Living room") //Получатель запроса (Receiver)
        val lightOn = LightOnCommand(light) //Команды с указанием Получателя (ConcreteCommand)

        val garageDoor = GarageDoor() //Получатель запроса (Receiver)
        val doorOpen = GarageDoorOpenCommand(garageDoor) //Команды с указанием Получателя  (ConcreteCommand)

        remote.setCommand(lightOn)
        remote.buttonWasPressed()
        remote.setCommand(doorOpen)
        remote.buttonWasPressed()
    }
}

