class Workshop(name: String, country: String, postCode: Int, city: String, street: String, phone: String) {
    private var name = name;
    private var country = country
    private var postCode = postCode
    private var city = city
    private var street = street
    private var phone = phone

    override fun toString(): String {
        return "Workshop(name='$name', country='$country', postCode=$postCode, city='$city', street='$street', phone='$phone')"
    }

}