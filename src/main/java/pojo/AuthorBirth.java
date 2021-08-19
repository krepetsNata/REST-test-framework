package pojo;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorBirth that = (AuthorBirth) o;
        return city.equals(that.city) && country.equals(that.country) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, date);
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
