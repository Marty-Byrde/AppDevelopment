fun main(args: Array<String>) {
    println("Hello World!")
    println("Program arguments: ${args.joinToString()}")

    var vehicle = Vehicle(1, "test",
        Brand("B1", "BC1", "43 9381283", "asdasd"),
        Workshop("W1", "WC1", 9020, "W2", "street", "43 1231237"),
        10, 100, 10.0, 100.0)

    vehicle.printInfo()
    ;


}