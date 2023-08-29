package command.recievers

class Light(
    private val roomName: String
) {
    fun on() {
        println("$roomName light is on")
    }

    fun off() {
        println("$roomName light is off")
    }
}

