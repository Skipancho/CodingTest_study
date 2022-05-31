package design_pattern

class AbstractFactory {

    fun main() {
        val man = HumanFactory.getHuman(ManFactory(20,"김철수"))
        val woman = HumanFactory.getHuman(WomanFactory(22,"김영희"))

        println(man.toString())
        println(woman.toString())
    }

    class HumanFactory{
        companion object{
            fun getHuman(factory: HumanAbstractFactory) = factory.createHuman()
        }
    }

    interface HumanAbstractFactory{
        fun createHuman() : Human
    }

    class ManFactory(
        private val age : Int,
        private val name : String
    ): HumanAbstractFactory{
        override fun createHuman(): Human {
            return Man(age, name)
        }
    }

    class WomanFactory(
        private val age : Int,
        private val name : String
    ): HumanAbstractFactory{
        override fun createHuman(): Human {
            return Woman(age, name)
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