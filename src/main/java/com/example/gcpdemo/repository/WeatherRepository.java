package com.example.gcpdemo.repository;

import com.example.gcpdemo.entity.WeatherLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherLog,Long> {
}
