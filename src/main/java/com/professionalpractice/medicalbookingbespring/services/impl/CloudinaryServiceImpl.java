package com.professionalpractice.medicalbookingbespring.services.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.professionalpractice.medicalbookingbespring.exceptions.InternalServerException;
import com.professionalpractice.medicalbookingbespring.services.CloudinaryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            if (file != null) {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                return uploadResult.get("url").toString();
            }

            return null;
        } catch (IOException e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
