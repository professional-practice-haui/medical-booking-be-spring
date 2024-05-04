package com.professionalpractice.medicalbookingbespring.controllers;

import com.professionalpractice.medicalbookingbespring.config.RestApiV1;
import com.professionalpractice.medicalbookingbespring.dtos.DepartmentDTO;
import com.professionalpractice.medicalbookingbespring.dtos.DoctorDTO;
import com.professionalpractice.medicalbookingbespring.dtos.ShiftDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.DepartmentRequest;
import com.professionalpractice.medicalbookingbespring.dtos.request.ShiftRequest;
import com.professionalpractice.medicalbookingbespring.dtos.response.PaginationResponse;
import com.professionalpractice.medicalbookingbespring.entities.Shift;
import com.professionalpractice.medicalbookingbespring.services.ShiftService;
import com.professionalpractice.medicalbookingbespring.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;

    @PostMapping("/shifts")
    public ResponseEntity<?> createShift(@RequestBody ShiftRequest shiftRequest) {
        ShiftDTO shift = shiftService.createShift(shiftRequest);
        return CustomResponse.success(HttpStatus.CREATED,"Tạo ca làm việc thành công",shift);
    }

    @GetMapping("/shifts")
    public ResponseEntity<?> getShifts(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int limit) {
        PageRequest pageRequest = PageRequest.of(
            page-1, limit,
            Sort.by("id").ascending());
        Page<ShiftDTO> shiftPage = shiftService.getShifts(pageRequest);
        long totalPages = shiftPage.getTotalElements();
        List<ShiftDTO> shifts = shiftPage.getContent();
        return CustomResponse.success(new PaginationResponse(page, limit, totalPages, shifts));
    }

    @PutMapping("/shifts/{shiftId}")
    public ResponseEntity<?> updateShift(@PathVariable Long shiftId,
                                         @RequestBody ShiftRequest shiftRequest) {
        ShiftDTO shift = shiftService.updateShift(shiftId, shiftRequest);
        return CustomResponse.success("Cập nhật thành công");
    }

    @DeleteMapping("/shifts/{shiftId}")
    public ResponseEntity<?> deleteShift(@PathVariable Long shiftId) {
        shiftService.deleteShiftById(shiftId);
        return CustomResponse.success("Cập nhật thành công");
    }


}
