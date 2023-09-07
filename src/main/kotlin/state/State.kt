package state

import kotlin.random.Random


interface State {
    fun insertQuarter()
    fun ejectQuarter()
    fun turnCrank()
    fun dispense()

}

class SoldState(private val gumballMachine: GumballMachine) : State {
    override fun insertQuarter() {
        println("Please wait, we’re already giving you a gumball")
    }

    override fun ejectQuarter() {
        println("“Sorry, you already turned the crank")
    }

    override fun turnCrank() {
        println("Turning twice doesn’t get you another gumball!")
    }

    override fun dispense() {
        println("A gumball comes rolling out the slot")
    }

}

class SoldOutState(private val gumballMachine: GumballMachine) : State {
    override fun insertQuarter() {
        println("You can’t insert a quarter, the machine is sold out")
    }

    override fun ejectQuarter() {
        println("You can’t eject, you haven’t inserted a quarter yet")
    }

    override fun turnCrank() {
        println("“You turned, but there are no gumballs")
    }

    override fun dispense() {
        gumballMachine.releaseBall()
        if (gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState())
        } else {
            println("Oops, out of gumballs!")
            gumballMachine.setState(gumballMachine.getSoldOutState())
        }
    }

}

class NoQuarterState(
    private val gumballMachine: GumballMachine
) : State {
    override fun insertQuarter() {
        println("You inserted a quarter")
        gumballMachine.setState(gumballMachine.getHasQuarterState())
    }

    override fun ejectQuarter() {
        println("You haven’t inserted a quarter")
    }

    override fun turnCrank() {
        println("“You turned, but there’s no quarter")
    }

    override fun dispense() {
        println("“You need to pay first")
    }

}

class HasQuarterState(private val gumballMachine: GumballMachine) : State {

    private val randomWinner = Random(System.currentTimeMillis())
    override fun insertQuarter() {
        println("You can’t insert another quarter")
    }

    override fun ejectQuarter() {
        println("Quarter returned")
        gumballMachine.setState(gumballMachine.getNoQuarterState())
    }

    override fun turnCrank() {
        println("You turned...")

        val winner = randomWinner.nextInt(10)
        if ((winner == 0) && (gumballMachine.getCount() > 1)) {
            gumballMachine.setState(gumballMachine.getWinnerState())
        } else {
            gumballMachine.setState(gumballMachine.getSoldState())
        }
    }

    override fun dispense() {
        println("No gumball dispensed")
    }

}

class WinnerState(private val gumballMachine: GumballMachine) : State {
    override fun insertQuarter() {
        println("Please wait, we’re already giving you a gumball")
    }

    override fun ejectQuarter() {
        println("“Sorry, you already turned the crank")
    }

    override fun turnCrank() {
        println("Turning twice doesn’t get you another gumball!")
    }


    override fun dispense() {
        gumballMachine.releaseBall()
        if (gumballMachine.getCount() == 0) {
            gumballMachine.setState(gumballMachine.getSoldOutState())
        } else {
            gumballMachine.releaseBall()
            println("YOU’RE A WINNER! You got two gumballs for your quarter")
            if (gumballMachine.getCount() > 0) {
                gumballMachine.setState(gumballMachine.getNoQuarterState())
            } else {
                println("YOops, out of gumballs!")
                gumballMachine.setState(gumballMachine.getSoldOutState())
            }
        }
    }
}


class GumballMachine(
    private var count: Int = 0
) {
    private val soldOut: State = SoldOutState(this)
    private val noQuarter: State = NoQuarterState(this)
    private val hasQuarter: State = HasQuarterState(this)
    private val sold: State = SoldState(this)
    private val winnerState: State = WinnerState(this)
    private var state: State = soldOut

    init {
        state = if (count > 0) {
            noQuarter
        } else {
            soldOut
        }
    }

    fun insertQuarter() {
        state.insertQuarter()
    }

    fun ejectQuarter() {
        state.ejectQuarter()
    }

    fun turnCrank() {
        state.turnCrank()
        state.dispense()
    }

    fun setState(state: State) {
        this.state = state
    }

    fun releaseBall() {
        println("A gumball comes rolling out the slot...")
        if (count != 0) {
            count -= 1
        }
    }

    fun getHasQuarterState(): State = hasQuarter
    fun getSoldOutState(): State = soldOut
    fun getSoldState(): State = sold
    fun getNoQuarterState(): State = noQuarter
    fun getWinnerState() = winnerState
    fun getCount(): Int = count

}


class GumballMachineTestDrive {
    fun test() {
        val gumballMachine = GumballMachine(5)
        println(gumballMachine)
        gumballMachine.insertQuarter()
        gumballMachine.turnCrank()
        println(gumballMachine)
        gumballMachine.insertQuarter()
        gumballMachine.turnCrank()
        gumballMachine.insertQuarter()
        gumballMachine.turnCrank()
        println(gumballMachine)
    }
}