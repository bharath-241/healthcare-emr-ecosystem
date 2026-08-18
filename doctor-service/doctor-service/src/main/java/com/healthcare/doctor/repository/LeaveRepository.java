package com.healthcare.doctor.repository;

import com.healthcare.doctor.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    List<Leave> findByDoctorId(Long doctorId);
}
