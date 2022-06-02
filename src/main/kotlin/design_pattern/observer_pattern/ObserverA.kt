package design_pattern.observer_pattern

class ObserverA : Observer {
    override fun notify() : Boolean{
        println("ObserverA action")
        return true
    }
}