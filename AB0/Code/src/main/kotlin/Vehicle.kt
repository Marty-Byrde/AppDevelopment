
class Vehicle(id: Int, name: String, brand: Brand, workshops: ArrayList<Workshop>, weight: Int, maxPermissionableWeight: Int, speed: Double, maxSpeed: Double)  {
    private var id = id;
    private var name = name
    private var brand = brand
    private var workshops = workshops;
    private var weight = weight;
    private var maxPermissionableWeight = maxPermissionableWeight;
    private var speed = speed;
    private var maxSpeed = maxSpeed

    fun isSpeedChangeValid(value: Int) : Boolean{
        if(value < 0 && speed - value >= 0) return true;
        if(value > 0 && speed + value <= maxSpeed) return true

        return false
    }


    fun accelerate() : Double{
        if(isSpeedChangeValid(10)) speed += 10;

        return speed;
    }

    fun brake() : Double{
        if(isSpeedChangeValid(-10)) speed -= 10;

        return speed;
    }



    fun drive(kilometers: Int) {
        for(km in kilometers downTo 0){
            for(accs in generateValues() downTo 0) accelerate();
            for(brk in generateValues() downTo 0) brake();

        }
    }

    fun getWorkshop(postcode: Int) : Workshop? {
        return workshops.find { w -> w.getPostCode() == postcode }
    }

    fun printInfo(){
        print("[")
        print("id= $id, ")
        print("name= $name, ")
        print("brand=$brand, ")
        print("workshops= $workshops, ")
        print("weight= $weight, ")
        print("maxPermissionableWeight= $maxPermissionableWeight, ")
        print("speed= $speed, ")
        print("maxSpeed= $maxSpeed, ")
        println("]")
    }
}

fun generateValues(min: Int = 3, max: Int = 7) : Int = (Math.random() * (max-min) + min).toInt()









//class Vehicle ( id: Int, name: String, var brand: Brand, var workshops: Workshop, var weight: Int, var maxPermissionableWeight: Int, var speed: Double, var maxSpeed: Double,
//               country: String,
//               phone: String
//) : Base(name, country, phone) {
//
//    fun accelerate() : Double{
//        if(speed + 10 <= maxSpeed) speed += 10;
//
//        return speed;
//    }
//
//    fun brake() : Double{
//        if(speed - 10 >= 0) speed -= 10;
//
//        return speed;
//    }
//
//    fun drive(kilometers: Int) {
//        val order = (Math.random() * 2 + 1).toInt()
//
//        if(order == 1){
//            brake();
//            accelerate();
//
//            accelerate();
//            brake()
//
//            accelerate()
//            brake()
//            return
//        }
//
//        brake()
//        accelerate()
//        accelerate()
//        accelerate()
//        brake()
//        brake()
//    }
//
//    fun printInfo(){
//        print("[")
//        print("id= $id, ")
//        print("name= $name, ")
//        print("brand= $brand, ")
//        print("workshops= $workshops, ")
//        print("weight= $weight, ")
//        print("maxPermissionableWeight= $maxPermissionableWeight, ")
//        print("speed= $speed, ")
//        print("maxSpeed= $maxSpeed, ")
//        println("]")
//    }
//}