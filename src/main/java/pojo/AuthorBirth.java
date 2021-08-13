package pojo;

import java.time.LocalDate;
import java.util.Date;

public class AuthorBirth {
    String city;
    String country;
    //LocalDate date;
    String date;

    public AuthorBirth() {

    }

    public AuthorBirth(String city, String country, String date) {
        this.city = city;
        this.country = country;
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDate() {
        return date;
    }

    public AuthorBirth setCity(String city) {
        this.city = city;
        return this;
    }

    public AuthorBirth setCountry(String country) {
        this.country = country;
        return this;
    }

    public AuthorBirth setDate(String date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "\"city\":\"" + city + "\"" +
                ", \"country\":\"" + country + "\"" +
                ", \"date\":\"" + date + "\"" +
                "}";
    }
}
