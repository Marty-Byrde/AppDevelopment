package com.example.madn.classes

import android.util.Log
import java.lang.reflect.Field

class Map (val players: ArrayList<Player>) {
    val figures : ArrayList<Figure> = ArrayList()

    // the player who is selected to make his move
    private var playersTurn: Player = players[0]




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
        var destination = FieldPosition(figure.position.fieldIdentifier + steps)

        // destination => inside other players housing spaces
        if(!isLegitMove(figure, destination)){
            // the destination would be inside the housing space of another player. Since this is not possible, the destination's position will be increased by the number of steps that the figure is inside the housing space.

            var invalidSteps = 0
            if(destination.fieldIdentifier in 55..58){
                // theoreticall inside black housing space
                invalidSteps = 58 - destination.fieldIdentifier
            }else if(destination.fieldIdentifier in 69..72){
                // theoreticall inside yellow housing space
                invalidSteps = 72 - destination.fieldIdentifier
            }else if(destination.fieldIdentifier in 27..30){
                // theoreticall inside green housing space
                invalidSteps = 30 - destination.fieldIdentifier
            }else if(destination.fieldIdentifier in 41..44){
                // theoreticall inside red housing space
                invalidSteps = 44 - destination.fieldIdentifier
            }

            Log.d("Logic", "Increasing the destination by $invalidSteps, in order to leave the *theoretical* housing space of someone else")
            destination = FieldPosition(destination.fieldIdentifier + invalidSteps)
        }

        val kicked = getFigureOnPosition(destination)
        kicked?.resetPosition()

        figure.move(destination)
    }

    /**
     * Sets a new player whose turn it is to make a move. When a [player] is provided, then this player can make his move. (e.g. when the dice threw 6)
     */
    fun nextPlayer(player: Player? = null){
        if(player != null) playersTurn = player;

        val indexOfPlayer = this.players.indexOf(this.playersTurn)

        if(indexOfPlayer + 1 == this.players.size)
            this.playersTurn = this.players[0]
        else
            this.playersTurn = this.players[indexOfPlayer + 1]

    }


    /**
     * Returns whether a [figure] can enter its home, depending on its [PlayerColor]. It can enter its home when it can enter the last [FieldPosition] with the given [steps].
     */
    fun isHomeable(figure: Figure, steps: Int) : Boolean{
        val home1: FieldPosition;
        val home2: FieldPosition;
        val home3: FieldPosition;
        val home4: FieldPosition;

        when(figure._player.color){
            PlayerColor.Blue -> {
                home1 = FieldPosition(58)
                home2 = FieldPosition(57)
                home3 = FieldPosition(56)
                home4 = FieldPosition(55)
            }
            PlayerColor.Red -> {
                home1 = FieldPosition(44)
                home2 = FieldPosition(43)
                home3 = FieldPosition(42)
                home4 = FieldPosition(41)
            }
            PlayerColor.Green -> {
                home1 = FieldPosition(30)
                home2 = FieldPosition(29)
                home3 = FieldPosition(28)
                home4 = FieldPosition(27)
            }
            PlayerColor.Yellow -> {
                home1 = FieldPosition(72)
                home2 = FieldPosition(71)
                home3 = FieldPosition(70)
                home4 = FieldPosition(69)
            }
        }

        if(isPositionFree(home1)){
            return hasEnoughSteps(figure, steps, home1)
        }

        if(isPositionFree(home2)){
            return hasEnoughSteps(figure, steps, home2)
        }

        if(isPositionFree(home3)){
            return hasEnoughSteps(figure, steps, home3)
        }

        if(isPositionFree(home4)){
            return hasEnoughSteps(figure, steps, home4)
        }

        return false;
    }


    /**
     * Returns a [Boolean] whether the [steps] are enough in order to reach a given [destination] from the [figure]'s [FieldPosition]
     */
    private fun hasEnoughSteps(figure: Figure, steps: Int, destination: FieldPosition) : Boolean{
        if(figure.position.fieldIdentifier + steps == destination.fieldIdentifier) return true;

        return false
    }

    /**
     * Returns a [Boolean] whether the [figure] is allowed to move to the given [destination].
     */
    private fun isLegitMove(figure: Figure, destination: FieldPosition) : Boolean {
        if(destination.type == FieldType.Normal) return true;
        else if(destination.type == FieldType.Yellow && figure._player.color == PlayerColor.Yellow) return true
        else if(destination.type == FieldType.Green && figure._player.color == PlayerColor.Green) return true
        else if(destination.type == FieldType.Red && figure._player.color == PlayerColor.Red) return true
        else if(destination.type == FieldType.Blue && figure._player.color == PlayerColor.Blue) return true


        return false;
    }
}