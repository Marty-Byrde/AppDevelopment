package com.example.madn.classes

enum class PlayerColor {
    Blue, Red, Green, Yellow
}

class Player (val alias: String, val color:PlayerColor) {
    val figures: ArrayList<Figure> = ArrayList()
    private var initHomePosition: Int = -1;


    init {
        when(color){
            PlayerColor.Blue   -> this.initHomePosition = 13
            PlayerColor.Red    -> this.initHomePosition = 9
            PlayerColor.Green  -> this.initHomePosition = 5;
            PlayerColor.Yellow -> this.initHomePosition = 1
        }

        // place figures on their starting positions
        var _incPos = this.initHomePosition
        for(count in 4 downTo 0) figures.add(Figure(FieldPosition(_incPos++), color))
    }
}