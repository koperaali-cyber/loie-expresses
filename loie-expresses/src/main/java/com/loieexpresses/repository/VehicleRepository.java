package com.loieexpresses.repository;

import com.loieexpresses.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
