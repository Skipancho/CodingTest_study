package design_pattern

class Factory {
    fun main(){
        val man = HumanFactory.getHuman("man",20,"김철수")
        val woman = HumanFactory.getHuman("woman",22,"김영희")
        println(man.toString())
        println(woman.toString())
    }

    class HumanFactory{
        companion object{
            fun getHuman(type : String, age: Int, name: String) : Human =
                if (type == "man") Man(age, name)
                else Woman(age, name)
        }
    }

    abstract class Human{
        abstract var age : Int
        abstract var name : String
        override fun toString(): String {
            return "name : $name , age : $age"
        }
    }

    data class Man(
        override var age : Int,
        override var name : String
    ) : Human(){
        override fun toString(): String {
            return "Man - ${super.toString()}"
        }
    }

    data class Woman(
        override var age : Int,
        override var name : String
    ) : Human(){
        override fun toString(): String {
            return "Woman - ${super.toString()}"
        }
    }
}


