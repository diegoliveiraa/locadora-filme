package com.diegoliveiraa.locadora_filme.repositories;

import com.diegoliveiraa.locadora_filme.entitys.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, String> {
    Optional<Location> findLocationByRenter(String renter);

    @Query("SELECT l FROM Location l WHERE l.devolutionStatus = :devolutionStatus AND l.dateDevolution < :currentDate")
    List<Location> findOverdueLocations(@Param("devolutionStatus") String devolutionStatus, @Param("currentDate") LocalDateTime currentDate);
}
