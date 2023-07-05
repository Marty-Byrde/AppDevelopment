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

    fun move(position: FieldPosition){
        // move animations

        this.position = position;
    }

}