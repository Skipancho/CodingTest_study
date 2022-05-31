package design_pattern

class AdapterPattern {

    fun main(){
        val adaptee  = Adaptee()
        Client().operation(Adapter(adaptee))
    }

    class Client{
        fun operation(target : Target){
            target.operation()
        }
    }

    open class Target{
        open fun operation(){
            println("Target Operation")
        }
    }
    class Adaptee{
        fun specificOperation(){
            println("Specific Operation")
        }
    }
    class Adapter(
        private val adaptee: Adaptee
    ) : Target() {
        override fun operation(){
            adaptee.specificOperation()
        }
    }
}