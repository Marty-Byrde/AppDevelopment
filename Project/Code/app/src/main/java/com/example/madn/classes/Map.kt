package com.example.madn.classes

class Map (val players: ArrayList<Player>) {
    val figures : ArrayList<Figure> = ArrayList()




    /**
     * Returns whether the requested [position] is occupied or not.
     */
    fun isPositionFree(position: FieldPosition): Boolean {
        return getFigureOnPosition(position) == null;
    }

    /**
     * Returns the figure on the [position]. If no figures is on this position it will return null.
     */
    private fun getFigureOnPosition(position: FieldPosition) : Figure? {
        for(player in players){
            for(figure in player.figures){
                if(figure.position.fieldIdentifier == position.fieldIdentifier) return figure
            }
        }
        return null
    }


    /**
     * Moves a figures to a given [FieldPosition] depending on the [figure]'s current postion and the [steps] it will make.
     */
    fun moveFigure(figure:Figure, steps: Int){
        val destination = FieldPosition(figure.position.fieldIdentifier + steps)

        val kicked = getFigureOnPosition(destination)
        kicked?.resetPosition()

        figure.move(destination)
    }

}