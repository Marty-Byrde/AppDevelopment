package com.example.madn.classes

class GameHandler (map: Map) {
    var map = map
        private set

    /**
     * Returns the an [Int] for the number the dice threw.
     */
    private fun throwDice() : Int{
        return (Math.random() * 6 + 1).toInt();
    }

    fun makeMove(figure: Figure){
        var dice = throwDice()
        while(dice == 6){
            map.moveFigure(figure, dice)
            dice = throwDice()
        }

        map.moveFigure(figure, dice)
    }
}