package com.trrp.trrp0

import com.trrp.trrp0.model.*
import com.trrp.trrp0.repository.*
import org.apache.tomcat.jni.Local
import org.springframework.stereotype.Service
import java.sql.DriverManager
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class MigrationService(
    private val cityRepository: CityRepository,
    private val countryRepository: CountryRepository,
    private val disciplineRepository: DisciplineRepository,
    private val trackRepository: TrackRepository,
    private val championshipRepository: ChampionshipRepository,
    private val raceRepository: RaceRepository,
    private val stageRepository: StageRepository
) {
    companion object {
        const val DB_URL = "jdbc:sqlite:/Users/korpatiy/Desktop/trrp0/src/main/resources/biathlon-lite.db"
    }

    fun migrate() {
        Class.forName("org.sqlite.JDBC");
        val conn =
            DriverManager.getConnection(DB_URL)

        val createStatement = conn.createStatement()
        val resultSet = createStatement.executeQuery("select * from t_biathlon")

        while (resultSet.next()) {

            var country = countryRepository.findByName(resultSet.getString("country_name"))
            if (country == null) {
                country = Country(name = resultSet.getString("country_name"))
                countryRepository.save(country)
            }


            var city = cityRepository.findByName(resultSet.getString("city_name"))
            if (city == null) {
                city = City(name = resultSet.getString("city_name"), country = country)
                cityRepository.save(city)
            }

            var discipline = disciplineRepository.findByName(resultSet.getString("disc_name"))
            if (discipline == null) {
                discipline = Discipline(
                    name = resultSet.getString("disc_name"),
                    nFireLines = resultSet.getInt("disc_lines"),
                    fine = resultSet.getInt("disc_fine")
                )
                disciplineRepository.save(discipline)
            }

            var championship = championshipRepository.findByName(resultSet.getString("champ_name"))
            if (championship == null) {
                championship = Championship(
                    name = resultSet.getString("champ_name"),
                    startDate = LocalDate.parse(resultSet.getString("champ_start")),
                    endDate = LocalDate.parse(resultSet.getString("champ_end"))
                )
                championshipRepository.save(championship)
            }

            var stage = stageRepository.findByName(resultSet.getString("stage_name"))
            if (stage == null) {
                stage = Stage(
                    name = resultSet.getString("stage_name"),
                    championship = championship,
                    city = city,
                    startDate = LocalDate.parse(resultSet.getString("stage_start")),
                    endDate = LocalDate.parse(resultSet.getString("stage_end"))
                )
                stageRepository.save(stage)
            }

            var track = trackRepository.findByLengthAndLocation(
                resultSet.getInt("track_location"),
                resultSet.getString("track_location")
            )

            if (track == null) {
                track = Track(
                    length = resultSet.getInt("track_length"),
                    location = resultSet.getString("track_location")
                )
                trackRepository.save(track)
            }

            val race = Race(
                sex = resultSet.getString("sex"),
                date = LocalDate.parse(resultSet.getString("race_date")),
                startTime = LocalDateTime.parse(resultSet.getString("start_time")),
                stage = stage, track = track, discipline = discipline
            )
            raceRepository.save(race)
        }
        resultSet.close()
        conn.close()
    }
}