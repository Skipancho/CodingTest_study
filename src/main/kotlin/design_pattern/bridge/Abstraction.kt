package design_pattern.bridge

abstract class Abstraction(
    private var implementor : Implementor
) {
    fun function(){
        implementor.implementation()
    }
}