package com.example.madn.classes


class FieldPosition (val fieldIdentifier: Int) {
    var fieldName: String
        private set

    init {
        when(fieldIdentifier){
            // Yellow Start positions
            1  -> this.fieldName = "YS-${twoDigit()}"
            2  -> this.fieldName = "YS-${twoDigit()}"
            3  -> this.fieldName = "YS-${twoDigit()}"
            4  -> this.fieldName = "YS-${twoDigit()}"

            // Yellow Home positions
            69 -> this.fieldName = "YH-${twoDigit()}"
            70 -> this.fieldName = "YH-${twoDigit()}"
            71 -> this.fieldName = "YH-${twoDigit()}"
            72 -> this.fieldName = "YH-${twoDigit()}"



            // Green Start Positions
            5  -> this.fieldName = "GS-${twoDigit()}"
            6  -> this.fieldName = "GS-${twoDigit()}"
            7  -> this.fieldName = "GS-${twoDigit()}"
            8  -> this.fieldName = "GS-${twoDigit()}"

            // Green Home positions
            27 -> this.fieldName = "GH-${twoDigit()}"
            28 -> this.fieldName = "GH-${twoDigit()}"
            29 -> this.fieldName = "GH-${twoDigit()}"
            30 -> this.fieldName = "GH-${twoDigit()}"




            // Red Start Positions
            9  -> this.fieldName = "RS-${twoDigit()}"
            10 -> this.fieldName = "RS-${twoDigit()}"
            11 -> this.fieldName = "RS-${twoDigit()}"
            12 -> this.fieldName = "RS-${twoDigit()}"

            // Red Home positions
            41 -> this.fieldName = "RH-${twoDigit()}"
            42 -> this.fieldName = "RH-${twoDigit()}"
            43 -> this.fieldName = "RH-${twoDigit()}"
            44 -> this.fieldName = "RH-${twoDigit()}"


            // Black Start Positions
            13 -> this.fieldName = "BS-${twoDigit()}"
            14 -> this.fieldName = "BS-${twoDigit()}"
            15 -> this.fieldName = "BS-${twoDigit()}"
            16 -> this.fieldName = "BS-${twoDigit()}"

            // Black Home positions
            55 -> this.fieldName = "BH-${twoDigit()}"
            56 -> this.fieldName = "BH-${twoDigit()}"
            57 -> this.fieldName = "BH-${twoDigit()}"
            58 -> this.fieldName = "BH-${twoDigit()}"

            // normal positions
            else -> this.fieldName = "FP-${twoDigit()}"
        }
    }

    /**
     * Returns the [fieldIdentifier] as a twoDigit String
     */
    private fun twoDigit() : String {
        if(this.fieldIdentifier.toString().isEmpty()) return "00"
        if(this.fieldIdentifier.toString().length == 1) return "0${this.fieldIdentifier}"

        return this.fieldIdentifier.toString()
    }

}