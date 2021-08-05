package kt

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.pow

/**
 * @author TunaSashimi
 * @date 2021/7/11 12:30
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */

//顶层声明
val site: String = "Google"

suspend fun main() {
    val singleName: String = SingleTon.name
    SingleTon.pringName()

    var user: User = User("user");
    var test: String = User.test
    //"?"加在变量名后，系统在任何情况不会报它的空指针异常。
    //"!!"加在变量名后，如果对象为null，那么系统一定会报异常！

    println(test)
    println(user.name?.length)
    println(user.name!!.length)

    var cons: Cons = Cons("name");
    var consName = cons.name;

    //
    getImage("Image")

    //
    println(2f.pow(10))

    //
    val a = A(6)
}

suspend fun getImage(imageId: String) {
    withContext(Dispatchers.IO) {
        getNetImage()
    }
}

fun getNetImage() {

}

class A {
    companion object Factory {
        operator fun invoke (any: Any) = any
    }
}

object SingleTon {
    var name: String = "SingleTon"
    fun pringName() {
        println(name)
    }
}

class User {
    constructor(name: String) {
        this.name = name
    }

    companion object {
        val test: String = "Test"
    }

    init {
        println("Initialzing")
    }

    var name: String? = null
}

class Cons(var name: String)

