open class Base (name: String, country : String, phone : String) {
    private var name = name;
    private var country = country;
    private var phone = phone

    override fun toString(): String {
        return "Base(name='$name', country='$country', phone='$phone')"
    }
}