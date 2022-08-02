package cinema

fun main() {
    println("Enter the number of rows:")
    val numberRows = readln().toInt()
    println("Enter the number of seats in each row:")
    val numberSeats = readln().toInt()

    val charS = MutableList(numberRows) {
        "S".repeat(numberSeats).toMutableList()
    }
    var charB = charS

    var strNumb = ""
    for (i in 1..numberSeats) {
        strNumb = "$strNumb $i"
    }

    var numberSeat = 0
    var numberRow = 0
    var countTicket = 0
    var strChar = ""
    var percentage = 0.00
    var sumOfTicketPrice = 0

    fun countAllMoney (numberRows: Int, numberSeats: Int):Int {
        return if (numberRows * numberSeats < 60) {
            numberRows * numberSeats * 10
        } else {
            (numberRows / 2) * numberSeats * 10 + (numberRows - numberRows / 2) * numberSeats * 8
        }
    }

    while (true) {
        println("""
1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit""")

        when (readln().toInt()) {
            0 -> break
            1 -> {
                if (numberRow == 0 && numberSeat == 0) {
                    println("\nCinema:\n $strNumb")
                    for (i in 1..numberRows) {
                        var strChar = "$i " + charS[i - 1].joinToString(" ")
                        println(strChar)
                    }
                } else {
                    charB[numberRow - 1][numberSeat - 1] = 'B'

                    println("\nCinema:\n $strNumb")
                    for (i in 1..numberRows) {
                        strChar = "$i " + charB[i - 1].joinToString(" ")
                        println(strChar)
                    }
                }
            }
            2 -> {
                while (true) {
                    println("\nEnter a row number:")
                    numberRow = readln().toInt()
                    println("Enter a seat number in that row:")
                    numberSeat = readln().toInt()
                    println()

                    try {
                        if (charB[numberRow - 1][numberSeat - 1] == 'B') {
                            println("That ticket has already been purchased!")
                            continue
                        } else {
                            if (numberRows * numberSeats < 60) {
                                println("Ticket price: $10")
                                sumOfTicketPrice += 10
                                charB[numberRow - 1][numberSeat - 1] = 'B'
                            } else {
                                if (numberRows / 2 >= numberRow) {
                                    println("Ticket price: $10")
                                    sumOfTicketPrice += 10
                                } else {
                                    println("Ticket price: $8")
                                    sumOfTicketPrice += 8
                                }
                                charB[numberRow - 1][numberSeat - 1] = 'B'
                            }
                            countTicket += 1
                            percentage = countTicket.toDouble() / (numberRows.toDouble() * numberSeats.toDouble()) * 100
                            break
                        }
                    } catch (e: Exception) {
                        println("Wrong input!")
                    }
                }
            }
            3 -> {
                println("\nNumber of purchased tickets: $countTicket")
                val formatPercentage = "%.2f".format(percentage)
                println("Percentage: $formatPercentage%")
                println("Current income: $$sumOfTicketPrice")
                println("Total income: $${countAllMoney(numberRows, numberSeats)}")
            }
        }
    }
}
