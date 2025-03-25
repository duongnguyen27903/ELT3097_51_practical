package com.example.myapplication.practical

fun main(){
    //2. Thông báo qua thiết bị di động
    val morningNotification = 51
    val eveningNotification = 135
    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)

    //3. Giá vé xem phim

    val child = 5
    val adult = 28
    val senior = 87
    val isMonday = true

    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")

    printFinalTemperature(27.0, "Celsius", "Fahrenheit") { 9.0 / 5.0 * it + 32 }
    printFinalTemperature(350.0, "Kelvin", "Celsius") { it - 273.15 }
    printFinalTemperature(10.0, "Fahrenheit", "Kelvin") { 5.0 / 9.0 * (it - 32) + 273.15 }

    val thisSong = Song("Lover","Taylor Swift","2022", 300_000_000 )
    println( thisSong.isPopular)
    thisSong.show()

    //6. Hồ sơ trên Internet
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)

    amanda.showProfile()
    atiqah.showProfile()

    //7. Điện thoại có thể gập lại
    val new_phone = FoldablePhone()
    new_phone.fold()
    new_phone.checkPhoneScreenLight()

    //8. Phiên đấu giá đặc biệt
    val winningBid = Bid(5000, "Private Collector")

    println("Item A is sold at ${auctionPrice(winningBid, 2000)}.")
    println("Item B is sold at ${auctionPrice(null, 3000)}.")
}

class Bid(val amount: Int, val bidder: String)

fun auctionPrice(bid: Bid?, minimumPrice: Int): Int {
    return bid?.amount ?: minimumPrice
}

class FoldablePhone(
    private var isFold : Boolean = true
) : Phone() {
    fun fold(){
        isFold = true
    }
    fun unfold(){
        isFold = false
    }
    override fun switchOn() {
        if (!isFold) {
            isScreenLightOn = true
        }
    }
}

open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class Person(
    private val name: String,
    private val age: Int,
    private val hobby: String?,
    private val referrer: Person?
) {
    fun showProfile() {
        println("Name: $name\nAge: $age")
        if(hobby != null) {
            print("Likes to $hobby. ")
        }
        if(referrer != null) {
            print("Has a referrer named ${referrer.name}")
            if(referrer.hobby != null) {
                print(", who likes to ${referrer.hobby}.")
            } else {
                print(".")
            }
        } else {
            print("Doesn't have a referrer.")
        }
    }
}

class Song(
    private val title : String,
    private val artist : String,
    private val year : String,
    private val timePlay : Int
) {
    var isPopular: Boolean = timePlay >= 1000
    fun show(){
        println("$title, performed by $artist, was released in $year.")
    }
}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
//    val finalMeasurement = BigDecimal(conversionFormula(initialMeasurement)).setScale(2, RoundingMode.HALF_UP.)
    val finalMeasurement = String.format(java.util.Locale.US,"%.2f",conversionFormula(initialMeasurement))
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    return when(age) {
        in 0..12 -> 15
        in 13..60 -> if (isMonday) 25 else 30
        in 61..100 -> 20
        else -> -1
    }
}

fun printNotificationSummary(numberOfMessages: Int) {
    if (numberOfMessages < 100) {
        println("You have $numberOfMessages notifications.")
    } else {
        println("Your phone is blowing up! You have 99+ notifications.")
    }
}