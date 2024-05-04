package com.professionalpractice.medicalbookingbespring.services.impl;

import com.professionalpractice.medicalbookingbespring.dtos.DepartmentDTO;
import com.professionalpractice.medicalbookingbespring.dtos.ShiftDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.ShiftRequest;
import com.professionalpractice.medicalbookingbespring.entities.Department;
import com.professionalpractice.medicalbookingbespring.entities.Shift;
import com.professionalpractice.medicalbookingbespring.exceptions.NotFoundException;
import com.professionalpractice.medicalbookingbespring.repositories.ShiftRepository;
import com.professionalpractice.medicalbookingbespring.services.ShiftService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService {

    private final ShiftRepository shiftRepository;

    private final ModelMapper modelMapper;

    @Override
    public ShiftDTO createShift(ShiftRequest shiftRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Chuyển đổi chuỗi thành LocalDate
        LocalDate date = LocalDate.parse(shiftRequest.getDate(), formatter);
            Shift shift = Shift.builder()
                .date(date)
                .time(shiftRequest.getTime())
                .place(shiftRequest.getPlace())
                .maxSlot(shiftRequest.getMaxSlot())
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
        return shiftPage.map(shift -> modelMapper.map(shift, ShiftDTO.class));
    }

    @Override
    public ShiftDTO updateShift(Long shiftId, ShiftRequest shiftRequest) {
        Shift shift = shiftRepository.findById(shiftId)
            .orElseThrow(() -> new NotFoundException("Không tìm thấy ca làm việc này"));
        if(shiftRequest.getDate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate localDate = LocalDate.parse(shiftRequest.getDate(), formatter);
            Shift newShift = Shift.builder()
                .date(localDate)
                .time(shiftRequest.getTime())
                .place(shiftRequest.getPlace())
                .maxSlot(shiftRequest.getMaxSlot())
                .build();
        }
        Shift newShift = Shift.builder()
            .time(shiftRequest.getTime())
            .place(shiftRequest.getPlace())
            .maxSlot(shiftRequest.getMaxSlot())
            .build();
        Shift saveShift = shiftRepository.save(newShift);
        return modelMapper.map(saveShift, ShiftDTO.class);
    }

    @Override
    public void deleteShiftById(Long shiftId) {
        shiftRepository.deleteById(shiftId);
    }


}
