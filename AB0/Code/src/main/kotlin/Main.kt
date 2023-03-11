fun main() {
    println("Main script has been started!")

    val dummyBrand = Brand("CarBrand", "Austria", "+43 182 1238138", "em@mail.at")

    val workshops = ArrayList<Workshop>()
    workshops.add(Workshop("Workshop Nr 1", "Autria", 9020, "Klagenfurt", "Street1", "+43 664 3827371"))
    workshops.add(Workshop("Workshop Nr 2", "Autria", 9030, "Villach", "Street2", "+43 664 3827371"))
    workshops.add(Workshop("Workshop Nr 3", "Autria", 9040, "Wolfsberg", "Street3", "+43 664 3827371"))
    workshops.add(Workshop("Workshop Nr 4", "Autria", 9050, "St. Veit", "Street4", "+43 664 3827371"))
    workshops.add(Workshop("Workshop Nr 5", "Autria", 9060, "Feldkirchen", "Street5", "+43 664 3827371"))

    val vehicles = ArrayList<Vehicle>()
    vehicles.add(Vehicle(1, "Audi A1", dummyBrand, workshops, 100, 12, generateValues(60, 5).toDouble(), 100.0))
    vehicles.add(Vehicle(2, "Audi A2", dummyBrand, workshops, 100, 12, generateValues(60, 5).toDouble(), 100.0))
    vehicles.add(Vehicle(3, "Audi A3", dummyBrand, workshops, 100, 12, generateValues(60, 5).toDouble(), 100.0))
    vehicles.add(Vehicle(4, "Audi A4", dummyBrand, workshops, 100, 12, generateValues(60, 5).toDouble(), 100.0))
    vehicles.add(Vehicle(5, "Audi A5", dummyBrand, workshops, 100, 12, generateValues(60, 5).toDouble(), 100.0))

    for(vehicle in vehicles) vehicle.printInfo()
}