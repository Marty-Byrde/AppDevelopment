package com.example.madn.classes

class GameHandler (map: Map) {
    var map = map
        private set

    /**
     * Returns the an [Int] for the number the dice threw.
     */
    fun throwDice() : Int{
        return (Math.random() * 6 + 1).toInt();
    }

}