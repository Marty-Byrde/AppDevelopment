package com.example.madn.classes

class Figure (position: FieldPosition, val _player: Player) {
    private val defaultPosition = position;

    var position = position
        private set


    /**
     * Sets the position of this [Figure] to its [defaultPosition]
     */
    fun resetPosition(){
        this.position = defaultPosition;
    }

    public fun move(steps: Int = -1, position: FieldPosition?){

    }

    private fun moveSteps(steps: Int){

    }

}