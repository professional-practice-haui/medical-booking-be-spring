package com.professionalpractice.medicalbookingbespring.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.professionalpractice.medicalbookingbespring.dtos.HealthFormDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.HealthFormRequest;
import com.professionalpractice.medicalbookingbespring.entities.HealthForm;
import com.professionalpractice.medicalbookingbespring.entities.Shift;
import com.professionalpractice.medicalbookingbespring.entities.User;
import com.professionalpractice.medicalbookingbespring.exceptions.NotFoundException;
import com.professionalpractice.medicalbookingbespring.repositories.HealthFormRepository;
import com.professionalpractice.medicalbookingbespring.repositories.ShiftRepository;
import com.professionalpractice.medicalbookingbespring.repositories.UserRepository;
import com.professionalpractice.medicalbookingbespring.services.HealthFormService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthFormServiceImpl implements HealthFormService {

        private final HealthFormRepository healthFormRepository;

        private final ModelMapper modelMapper;

        private final UserRepository userRepository;

        private final ShiftRepository shiftRepository;

        @Override
        public HealthFormDTO createHealthForm(HealthFormRequest healthFormRequest) {
                String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = userRepository.findByEmail(userEmail)
                                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));
                Shift shift = shiftRepository.findById(healthFormRequest.getShift())
                                .orElseThrow(() -> new NotFoundException("Không tìm thấy ca làm việc này này"));

                HealthForm healthForm = HealthForm.builder()
                                .user(user)
                                .namePatient(healthFormRequest.getNamePatient())
                                .email(healthFormRequest.getEmail())
                                .phoneNumber(healthFormRequest.getPhoneNumber())
                                .address(healthFormRequest.getAddress())
                                .shift(shift)
                                .reason(healthFormRequest.getReason())
                                .cccd(healthFormRequest.getCccdUrl())
                                .bhyt(healthFormRequest.getBhytUrl())
                                .deniedReason(healthFormRequest.getDeniedReason())
                                .status(0)
                                .build();

                HealthForm saveHealthForm = healthFormRepository.save(healthForm);
                return modelMapper.map(saveHealthForm, HealthFormDTO.class);
        }

        @Override
        public Page<HealthFormDTO> getHealthFormByUserId(Long userId, PageRequest pageRequest) {
                Page<HealthForm> healthFormPage = healthFormRepository.queryHealthForm(userId, pageRequest);

                return healthFormPage.map(theHealthForm -> modelMapper.map(theHealthForm, HealthFormDTO.class));
        }

        @Override
        public Page<HealthFormDTO> getHealthForms(PageRequest pageRequest) {
                Page<HealthForm> healthFormPage = healthFormRepository.queryHealthForm(pageRequest);
                return healthFormPage.map(theHealthForm -> modelMapper.map(theHealthForm, HealthFormDTO.class));
        }

        @Override
        public HealthFormDTO updateHealthForm(Long healthFormId, HealthFormRequest healthFormRequest) {
                HealthForm healthForm = healthFormRepository.findById(healthFormId)
                                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn khám"));

                if (healthFormRequest.getStatus() != 0) {
                        healthForm.setStatus(healthFormRequest.getStatus());
                }

                if (healthFormRequest.getDeniedReason() != null) {
                        healthForm.setDeniedReason(healthFormRequest.getDeniedReason());
                }

                HealthForm saveHealthForm = healthFormRepository.save(healthForm);
                return modelMapper.map(saveHealthForm, HealthFormDTO.class);
        }

        @Override
        public void deleteHealthFormById(Long healthFormId) {
                HealthForm healthForm = healthFormRepository.findById(healthFormId)
                                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn khám"));
                healthFormRepository.deleteById(healthFormId);
        }

        @Override
        public Page<HealthFormDTO> getHistory(String userEmail, PageRequest pageRequest) {
                User user = userRepository.findByEmail(userEmail)
                                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));
                Page<HealthForm> healthFormPage = healthFormRepository.queryHealthForm(user.getId(), pageRequest);

                return healthFormPage.map(theHealthForm -> modelMapper.map(theHealthForm, HealthFormDTO.class));
        }

        @Override
        public Page<HealthFormDTO> getHealthFormByStatus(String status, PageRequest pageRequest) {
                Page<HealthForm> healthFormPage = healthFormRepository.queryHealthFormByStatus(status, pageRequest);

                return healthFormPage.map(theHealthForm -> modelMapper.map(theHealthForm, HealthFormDTO.class));
        }

        @Override
        public HealthFormDTO updateStatusOfHealthForm(Long heathFormId, HealthFormRequest healthFormRequest) {
                HealthFormDTO healthFormDTO = updateHealthForm(heathFormId, healthFormRequest);

                return healthFormDTO;
        }
}
