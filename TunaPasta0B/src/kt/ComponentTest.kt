package kt

fun main() {
    //字符模板,避免拼接字符
    var number = 100
    println("商品数量有$number")

    val str = "hello"
    // str length : 5
    print("$str length: ${str.length}")

    //
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    list.forEach { print(it) }
    // 映射
    val listUp = list.map { it + 2 }
    // 过滤
    val listFilter = list.filter { it % 2 == 0 }
    // asSequence 懒处理 map filter中都存在遍历操作 asSequence可以将它们合并起来 只存在一次遍历 提升性能
    val listMore = list.asSequence().map { it + 2 }.filter { it % 2 == 0 }.toList()

    // 解构申明
    val people = People("TunaSashimi", 18, 2021)
    var (myName, myAge, myYear) = people//注意这行代码，解构申明
    println("my name is $myName, i am $myAge,year is $myYear")
}


class People(val name: String, val age: Int, val year: Int) {
    operator fun component1() = name //注意
    operator fun component2() = age  //注意
    operator fun component3() = year  //注意
}