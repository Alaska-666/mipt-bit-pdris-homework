package ru.mipt.bit.homework.weathercurrencyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mipt.bit.homework.weathercurrencyapp.entities.WeatherEntity;
import ru.mipt.bit.homework.weathercurrencyapp.entities.WeatherEntityId;

public interface WeatherRepository extends JpaRepository<WeatherEntity, WeatherEntityId> {
}
