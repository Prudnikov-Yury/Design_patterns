package command.concrete_commands

import command.Command
import command.recievers.Light

class LightOnCommand(
    private val light: Light
) : Command {
    override fun execute() {
        light.on()
    }

    override fun undo() {
        light.off()
    }
}

class LightOffCommand(
    private val light: Light
) : Command {
    override fun execute() {
        light.off()
    }

    override fun undo() {
        light.on()
    }
}

class StereoOnWithCDCommand(private val stereo: Stereo) : Command {
    override fun execute() {
        stereo.on()
        stereo.setCd()
        stereo.setVolume(11)
    }

    override fun undo() {
        stereo.off()
    }

}

class StereoOffCommand(private val stereo: Stereo) : Command {
    override fun execute() {
        stereo.off()
    }

    override fun undo() {
        stereo.on()
        stereo.setCd()
        stereo.setVolume(11)
    }

}

class Stereo(private val roomName: String) {
    fun on() {
        println("$roomName stereo on")
    }

    fun off() {
        println("$roomName stereo off")
    }

    fun setCd() {
        println("$roomName stereo setCd")
    }

    fun setVolume(volume: Int) {
        println("$roomName stereo setVolume $volume")
    }
}

