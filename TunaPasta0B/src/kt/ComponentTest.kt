package kt

// 解构申明
fun main() {
    val people = People("TunaSashimi", 18, 2021)
    var (myName, myAge, myYear) = people//注意这行代码，解构申明
    println("my name is $myName, i am $myAge,year is $myYear")
}

class People(val name: String, val age: Int, val year: Int) {
    operator fun component1() = name //注意
    operator fun component2() = age  //注意
    operator fun component3() = year  //注意
}