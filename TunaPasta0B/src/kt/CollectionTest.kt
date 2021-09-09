package kt

fun main() {

    //在 Kotlin 的集合类中不仅仅能持有普通对象，而且能够持有函数类型的变量。
    val funlist: List<(Int) -> Boolean> = listOf({ it % 2 == 0 }, { it % 2 == 1 })
    val list1 = listOf(1, 2, 3, 4, 5, 6, 7)
    val filter1 = list1.filter(funlist[0])
    val filter2 = list1.filter(funlist[1])
    println("filter1==$filter1")
    println("filter2==$filter2")
    //传入第1个函数funlist[O]，返回[2, 4, 6]
    //传入第2个函数funlist[1]，返回[1，3, 5, 7]


    val list2 = listOf(1, 2, 3, 4, 5, 6, 7)//声明并初始化一个 List
    val set = setOf(1, 2, 3, 4, 5, 6, 7)//声明并初始化一个 Set
    val map = mapOf(1 to "a", 2 to "b", 3 to "c")//声明并初始化一个Map
    //map函数对每个元素进行乘方操作，返回[1, 4, 9, 16, 25, 36， 49]
    list2.map { it * it }
    //map 函数对每个元素进行加 1操作，返回[2, 3, 4, 5, 6, 7, 8]
    set.map { it + 1 }
    //map 函数对每个元素后加上字符$，返回[a$，b$， c$]
    map.map { it.value + "$" }

    val strlist = listOf("a", "b", "c")
    //map 函数 ， 每个元素 it 映射之后返回一个List，这个List 中有 4 个元素，分别是 it+1,
    //it+2,it+3,it+4
    strlist.map { listOf(it + 1, it + 2, it + 3, it + 4) }
    //返回结果如下 :
    //[[a1, a2, a3, a4], [b1, b2, b3, b4] , [c1, c2, c3, c4]] //嵌套 List

    //Kotlin 中还提供了 一个 flatten()函数，效果是把嵌套的 List 结构“平铺”，变成一层 的 结构，代码示例如下 :
    strlist.map { listOf(it + 1, it + 2, it + 3, it + 4) }.flatten()
    //输出如下 :
    // [a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4]

    //创建一个持有 Student对象 List
    val studentList = listOf(
        Student(1, "Jack", 18, 90),
        Student(2, "Rose", 17, 80),
        Student(3, "Alice", 16, 70)
    )

    //过滤出 studentList 中年龄大于等于 18 岁的Student 对象
    studentList.filter { it.age >= 18 }

    //通过访问下标来过滤
    val list3= listOf(1, 2, 3, 4, 5, 6, 7)
    list3.filterIndexed {  index, it -> index % 2 == 0 && it > 3  }

    //对一个 List列表进行元素去重，可以直接调用 distinct()函数:
    val dupList = listOf(1, 1, 2, 2, 3, 3, 3)
    dupList.distinct() //去重函数， 返回[1，2, 3]
}

data class Student(var id: Long, var name: String, var age: Int, var score: Int) {
    override fun toString(): String {
        return "Student(id=$id, name='$name'， age=$age, score=$score)"
    }
}
