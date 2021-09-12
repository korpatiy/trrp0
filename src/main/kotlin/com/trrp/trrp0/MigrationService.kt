package com.trrp.trrp0

import com.trrp.trrp0.model.City
import com.trrp.trrp0.model.Country
import com.trrp.trrp0.repository.CityRepository
import com.trrp.trrp0.repository.CountryRepository
import org.springframework.stereotype.Service
import java.sql.DriverManager

@Service
class MigrationService(
    private val cityRepository: CityRepository,
    private val countryRepository: CountryRepository
) {

    fun migrate() {
        val city = City()
        city.name = "kek"

        val country = countryRepository.findByName("rus")
        city.country = country
        cityRepository.save(city)

        Class.forName("org.sqlite.JDBC");
        val conn =
            DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Slava\\Desktop\\trrp0\\src\\main\\resources\\biathlon-lite.db")

        val createStatement = conn.createStatement()
        val resultSet = createStatement.executeQuery("select * from t_biathlon")

        while (resultSet.next()) {
            val country = Country(name = resultSet.getString("country_name"))
            val city = City(name = resultSet.getString("city_name"), country = country)
        }
        resultSet.close()
        conn.close()
    }
}