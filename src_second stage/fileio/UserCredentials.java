package fileio;

/**
 * IO Class for user's credentials
 */
public class UserCredentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;

    /**
     * No-arguments constructor
     */
    public UserCredentials() {
    }

    /**
     * Getter for name
     * @return String object representing user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name String object for name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for password
     * @return String object representing user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password String object for password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for user's account type
     * @return String object representing user's account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Setter for user's account type
     * @param accountType String object for account type
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Getter for user's country
     * @return String object representing user's country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter for user's country
     * @param country String object for country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Getter for user's balance
     * @return String object representing user's balance
     */
    public String getBalance() {
        return balance;
    }

    /**
     * Setter for balance
     * @param balance String object for balance
     */
    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", accountType='" + accountType + '\'' +
                ", country='" + country + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
