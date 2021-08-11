package pojo;

import java.time.LocalDate;
import java.util.Date;

public class AuthorBirth {
    String city;
    String country;
    LocalDate date;

    public AuthorBirth() {

    }

    public AuthorBirth(String city, String country, LocalDate date) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "\nAuthorBirthData{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
