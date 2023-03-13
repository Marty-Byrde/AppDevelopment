package com.example.madn.classes

class Map (val players: ArrayList<Player>) {
    val figures : ArrayList<Figure> = ArrayList()

    // the player who is selected to make his move
    private var movingPlayer: Player = players[0]




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

    /**
     * Sets a new player whose turn it is to make a move. When a [player] is provided, then this player can make his move. (e.g. when the dice threw 6)
     */
    fun nextPlayer(player: Player? = null){
        if(player != null) movingPlayer = player;

        val indexOfPlayer = this.players.indexOf(this.movingPlayer)

        if(indexOfPlayer + 1 == this.players.size)
            this.movingPlayer = this.players[0]
        else
            this.movingPlayer = this.players[indexOfPlayer + 1]

    }

}