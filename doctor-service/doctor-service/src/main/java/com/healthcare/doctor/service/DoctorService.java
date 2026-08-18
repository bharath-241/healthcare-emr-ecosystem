package com.healthcare.doctor.service;

import com.healthcare.doctor.dto.DoctorRequest;
import com.healthcare.doctor.dto.DoctorResponse;
import com.healthcare.doctor.entity.Doctor;
import com.healthcare.doctor.entity.Leave;
import com.healthcare.doctor.entity.Schedule;
import com.healthcare.doctor.repository.DoctorRepository;
import com.healthcare.doctor.repository.LeaveRepository;
import com.healthcare.doctor.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ScheduleRepository scheduleRepository;
    private final LeaveRepository leaveRepository;

    public DoctorService(DoctorRepository doctorRepository,
                         ScheduleRepository scheduleRepository,
                         LeaveRepository leaveRepository) {
        this.doctorRepository = doctorRepository;
        this.scheduleRepository = scheduleRepository;
        this.leaveRepository = leaveRepository;
    }

    public DoctorResponse createDoctor(DoctorRequest request) {

        if (doctorRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Doctor email already exists");
        }

        Doctor doctor = new Doctor(
                request.getName(),
                request.getSpecialization(),
                request.getEmail(),
                request.getPhone(),
                request.getQualification()
        );

        return convertToResponse(doctorRepository.save(doctor));
    }

    public List<DoctorResponse> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return convertToResponse(doctor);
    }

    public DoctorResponse updateDoctor(Long id, DoctorRequest request) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setName(request.getName());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setEmail(request.getEmail());
        doctor.setPhone(request.getPhone());
        doctor.setQualification(request.getQualification());

        return convertToResponse(doctorRepository.save(doctor));
    }

    public void deleteDoctor(Long id) {

        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor not found");
        }

        doctorRepository.deleteById(id);
    }

    public Schedule addSchedule(Long doctorId, Schedule schedule) {

        if (!doctorRepository.existsById(doctorId)) {
            throw new RuntimeException("Doctor not found");
        }

        schedule.setDoctorId(doctorId);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getSchedules(Long doctorId) {

        if (!doctorRepository.existsById(doctorId)) {
            throw new RuntimeException("Doctor not found");
        }

        return scheduleRepository.findByDoctorId(doctorId);
    }

    public Leave addLeave(Long doctorId, Leave leave) {

        if (!doctorRepository.existsById(doctorId)) {
            throw new RuntimeException("Doctor not found");
        }

        leave.setDoctorId(doctorId);
        return leaveRepository.save(leave);
    }

    public List<Leave> getLeaves(Long doctorId) {

        if (!doctorRepository.existsById(doctorId)) {
            throw new RuntimeException("Doctor not found");
        }

        return leaveRepository.findByDoctorId(doctorId);
    }

    private DoctorResponse convertToResponse(Doctor doctor) {
        return new DoctorResponse(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getQualification()
        );
    }
}
