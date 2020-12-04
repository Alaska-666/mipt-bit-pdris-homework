package ru.mipt.bit.homework.weathercurrencyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mipt.bit.homework.weathercurrencyapp.entities.CurrencyEntity;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, String> {
}
