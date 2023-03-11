class Brand (name: String, country: String, phone: String, email: String) {
    private var name = name
    private var country = country
    private var phone = phone
    private var email = email;

    override fun toString(): String {
        return "[Brand: name=$name, country=$country, phone=$phone, email=$email"
    }
}