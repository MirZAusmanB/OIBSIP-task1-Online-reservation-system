class Login {
    private final String VALID_USERNAME = "MUB";
    private final String VALID_PASSWORD = "1234";
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean userAuthentication() {
        return VALID_USERNAME.equalsIgnoreCase(username) && VALID_PASSWORD.equals(password);
    }
}