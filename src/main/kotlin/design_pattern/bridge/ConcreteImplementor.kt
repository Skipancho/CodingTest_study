package design_pattern.bridge

class ConcreteImplementor1 : Implementor{
    override fun implementation() {
        println("Implementor1 action")
    }
}

class ConcreteImplementor2 : Implementor{
    override fun implementation() {
        println("Implementor2 action")
    }
}