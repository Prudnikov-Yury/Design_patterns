package command.invokers

import command.concrete_commands.*
import command.recievers.GarageDoor
import command.recievers.Light

//Класс RemoteLoader создает
//объекты команд, связываемые с ячейками пульта.
//Каждый объект команды
//инкапсулирует запрос к некоторому устройству.

class RemoteLoader {

    fun loadRemotesTest() {
        val remoteControl = RemoteControlWithUndo(recieversCount = 7)

        //создание удаленных устройств
        val livingRoomLight = Light("Living Room")
        val kitchenLight = Light("Kitchen")
        val garageDoor = GarageDoor()
        val stereo = Stereo("Living Room")

        //создание конкретных команд по устройства
        val livingRoomLightOn = LightOnCommand(livingRoomLight)
        val livingRoomLightOff = LightOffCommand(livingRoomLight)
        val kitchenLightLightOn = LightOnCommand(kitchenLight)
        val kitchenLightLightOff = LightOffCommand(kitchenLight)
        val doorOpen = GarageDoorOpenCommand(garageDoor)
        val doorClose = GarageDoorCloseCommand(garageDoor)
        val stereoOnWithCD = StereoOnWithCDCommand(stereo)
        val stereoOff = StereoOffCommand(stereo)

        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff)
        remoteControl.setCommand(1, kitchenLightLightOn, kitchenLightLightOff)
        remoteControl.setCommand(2, stereoOnWithCD, stereoOff)
        remoteControl.setCommand(3, doorOpen, doorClose)


        remoteControl.onButtonWasPushed(0)
        remoteControl.offButtonWasPushed(0)
        remoteControl.undoButtonWasPushed()
        remoteControl.onButtonWasPushed(1)
        remoteControl.offButtonWasPushed(1)
        remoteControl.onButtonWasPushed(2)
        remoteControl.offButtonWasPushed(2)
        remoteControl.onButtonWasPushed(3)
        remoteControl.offButtonWasPushed(3)
        remoteControl.undoButtonWasPushed()
        remoteControl.onButtonWasPushed(4)
        remoteControl.offButtonWasPushed(4)


    }
}