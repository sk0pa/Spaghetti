package com.epam.tal5.khalii.spaghetti.data;

public class Configuration {
    private static ThreadLocal<Configuration> instance = new ThreadLocal<Configuration>();
    private PropertyFactory properties;
    private String browser;
    private String url;
    private String filepath;
    private String user_first;
    private String user_second;
    private String password_first;
    private String password_second;

    public static void setUpConfiguration(){
        final Configuration configuration = new Configuration();
        configuration.setBrowser(PropertyFactory.getProperty("browser_name"));
        configuration.setUrl(PropertyFactory.getProperty("base_url"));
        configuration.setFilepath(PropertyFactory.getProperty("filepath"));
        configuration.setUser_first(PropertyFactory.getProperty("user_first"));
        configuration.setPassword_first(PropertyFactory.getProperty("password_first"));
        configuration.setUser_second(PropertyFactory.getProperty("user_second"));
        configuration.setPassword_second(PropertyFactory.getProperty("password_second"));

        setConfig(configuration);
    }

    public synchronized static void setConfig(Configuration config) {

        if (config == null) {
            instance.remove();
        } else {
            instance.set(config);
        }
    }

    public static Configuration getConfig() {
        return instance.get();
    }

    public String getPassword_second() {
        return password_second;
    }

    public void setPassword_second(String password_second) {
        this.password_second = password_second;
    }

    public String getPassword_first() {
        return password_first;
    }

    public void setPassword_first(String password_first) {
        this.password_first = password_first;
    }

    public String getUser_second() {
        return user_second;
    }

    public void setUser_second(String user_second) {
        this.user_second = user_second;
    }

    public String getUser_first() {
        return user_first;
    }

    public void setUser_first(String user_first) {
        this.user_first = user_first;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
