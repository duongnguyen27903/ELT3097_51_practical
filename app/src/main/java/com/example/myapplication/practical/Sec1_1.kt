package com.example.myapplication.practical

fun main (){
    //2. In thông báo
    println("Use the val keyword when the value doesn't change.\n" +
            "Use the var keyword when the value can change.\n" +
            "When you define a function, you define the parameters that can be passed to it.\n" +
            "When you call a function, you pass arguments for the parameters.")

    //3. Sửa lỗi biên dịch
        println("New chat message from a friend")

    //4. Mẫu chuỗi
        var discountPercentage: Int = 0
        var offer: String = ""
        val item : String = "Google Chromecast"
        discountPercentage = 20
        offer = "Sale - Up to $discountPercentage% discount on $item! Hurry up!"
        println(offer)

    //5. Ghép chuỗi
        val numberOfAdults = 20
        val numberOfKids = 30
        val total = numberOfAdults + numberOfKids
        println("The total party size is: $total")

    //6. Định dạng thông báo
        val baseSalary = 5000
        val bonusAmount = 1000
        val totalSalary = "$baseSalary + $bonusAmount"
        println("Congratulations for your bonus! You will receive a total of $totalSalary (additional bonus).")

    //7. Triển khai các phép toán cơ bản
    val firstNumber = 10
    val secondNumber = 5
    val thirdNumber = 8

    val result = add(firstNumber, secondNumber)
    val anotherResult = add(firstNumber, thirdNumber)
    val substractResult = substract(firstNumber,secondNumber)

    println("$firstNumber + $secondNumber = $result")
    println("$firstNumber + $thirdNumber = $anotherResult")
    println("$firstNumber - $secondNumber = $substractResult")

    //8. Tham số mặc định
        val operatingSystem = "Chrome OS"
        val emailId = "sample@gmail.com"
        println(displayAlertMessage())
        println(displayAlertMessage(operatingSystem, emailId))

        val firstUserEmailId = "user_one@gmail.com"

        // The following line of code assumes that you named your parameter as emailId.
        // If you named it differently, feel free to update the name.
        println(displayAlertMessage(emailId = firstUserEmailId))
        println()

        val secondUserOperatingSystem = "Windows"
        val secondUserEmailId = "user_two@gmail.com"

        println(displayAlertMessage(secondUserOperatingSystem, secondUserEmailId))
        println()

        val thirdUserOperatingSystem = "Mac OS"
        val thirdUserEmailId = "user_three@gmail.com"

        println(displayAlertMessage(thirdUserOperatingSystem, thirdUserEmailId))
        println()

    //9. Máy đếm bước
        val steps = 4000
        val caloriesBurned = stepToCalories(steps);
        println("Walking $steps steps burns $caloriesBurned calories")

    //10. So sánh 2 số
    println("Have I spent more time using my phone today: ${compareTime(300, 250)}")
    println("Have I spent more time using my phone today: ${compareTime(300, 300)}")
    println("Have I spent more time using my phone today: ${compareTime(200, 220)}")

    //11. Di chuyển mã trùng lặp vào một hàm
        weatherInfo("Ankara",27,31,82)
        weatherInfo("Tokyo",32,36,10)
        weatherInfo("Cape Town",59,64,2)
        weatherInfo("Guatemala City",50,55,7)
    //12. Mã giải pháp


}

fun add( firstNumber : Int, secondNumber : Int ) : Int{
    return firstNumber + secondNumber;
}

fun substract( firstNumber : Int, secondNumber : Int ) : Int{
    return firstNumber - secondNumber;
}

fun displayAlertMessage(operatingSystem : String = "UnknownOS", emailId : String = "emailId") : String{
    return "There's a new sign-in request on $operatingSystem for your Google Account $emailId."
}

fun stepToCalories(NumberOFStepS: Int): Double {
    val caloriesBurnedforEachStep = 0.04
    val totalCALORIESburned = NumberOFStepS * caloriesBurnedforEachStep
    return totalCALORIESburned
}

fun compareTime(num1 : Int, num2 : Int) : Boolean{
    if( num1 > num2 )return true;
    return false;
}

fun weatherInfo(city : String, low_temp : Int, high_temp : Int, rainChance : Int ) {
    println("City: $city")
    println("Low temperature: $low_temp, High temperature: $high_temp")
    println("Chance of rain: $rainChance%")
    println()
}

