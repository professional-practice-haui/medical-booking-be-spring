package com.professionalpractice.medicalbookingbespring.services.impl;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.professionalpractice.medicalbookingbespring.dtos.ShiftDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.ShiftRequest;
import com.professionalpractice.medicalbookingbespring.entities.Doctor;
import com.professionalpractice.medicalbookingbespring.entities.Shift;
import com.professionalpractice.medicalbookingbespring.exceptions.NotFoundException;
import com.professionalpractice.medicalbookingbespring.repositories.DoctorRepository;
import com.professionalpractice.medicalbookingbespring.repositories.HealthFormRepository;
import com.professionalpractice.medicalbookingbespring.repositories.ShiftRepository;
import com.professionalpractice.medicalbookingbespring.services.ShiftService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService {

    private final ShiftRepository shiftRepository;

    private final ModelMapper modelMapper;

    private final HealthFormRepository healthFormRepository;

    private final DoctorRepository doctorRepository;

    @Override
    public ShiftDTO createShift(ShiftRequest shiftRequest) {
        Doctor existingDoctor;
        if (shiftRequest.getDoctor() != null) {
            existingDoctor = doctorRepository.findById(shiftRequest.getDoctor())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy bác sĩ này"));
        } else {
            existingDoctor = null;
        }
        LocalDate date = LocalDate.parse(shiftRequest.getDate());

        Shift shift = Shift.builder()
                .date(date)
                .time(shiftRequest.getTime())
                .place(shiftRequest.getPlace())
                .maxSlot(shiftRequest.getMaxSlot())
                .doctor(existingDoctor)
                .note(shiftRequest.getNote())
                .build();
        Shift saveShift = shiftRepository.save(shift);
        return modelMapper.map(saveShift, ShiftDTO.class);
    }

    @Override
    public ShiftDTO getShiftById(Long shiftId) {
        return null;
    }

    @Override
    public Page<ShiftDTO> getShifts(PageRequest pageRequest) {
        Page<Shift> shiftPage = shiftRepository.queryShift(pageRequest);
        return shiftPage.map(shift -> {
            ShiftDTO shiftDTO = modelMapper.map(shift, ShiftDTO.class);
            shiftDTO.setSlot(healthFormRepository.countByShiftId(shift.getId()));
            return shiftDTO;
        });
    }

    @Override
    public ShiftDTO updateShift(Long shiftId, ShiftRequest shiftRequest) {
        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy ca làm việc này"));
        Doctor existingDoctor;
        if (shiftRequest.getDoctor() != null) {
            existingDoctor = doctorRepository.findById(shiftRequest.getDoctor())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy bác sĩ này"));
        } else {
            existingDoctor = null;
        }
        LocalDate date = LocalDate.parse(shiftRequest.getDate());

        Shift newShift = Shift.builder()
                .id(shiftId)
                .date(date)
                .time(shiftRequest.getTime())
                .place(shiftRequest.getPlace())
                .maxSlot(shiftRequest.getMaxSlot())
                .doctor(existingDoctor)
                .note(shiftRequest.getNote())
                .build();

        Shift saveShift = shiftRepository.save(newShift);
        return modelMapper.map(saveShift, ShiftDTO.class);
    }

    @Override
    public void deleteShiftById(Long shiftId) {
        shiftRepository.deleteById(shiftId);
    }

}
