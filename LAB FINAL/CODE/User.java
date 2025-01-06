class User {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean authenticate(String inputName, String inputPassword) {
        return this.name.equals(inputName) && this.password.equals(inputPassword);
    }
}