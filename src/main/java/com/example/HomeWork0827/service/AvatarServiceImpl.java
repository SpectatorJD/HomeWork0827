package com.example.HomeWork0827.service;

import com.example.HomeWork0827.exception.AvatarNotFoundException;
import com.example.HomeWork0827.model.Avatar;
import com.example.HomeWork0827.model.Student;
import com.example.HomeWork0827.repository.AvatarRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentService studentService;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    @Override
    public Avatar uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        Student student = studentService.findStudent(studentId);
        Path pathFile = Path.of("./avatar", UUID.randomUUID() + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(pathFile.getParent());
        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(pathFile, StandardOpenOption.CREATE_NEW);

                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setStudent(student);
        avatar.setPath(pathFile.toString());
        avatar.setSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        return avatarRepository.save(avatar);

    }

    @Override
    public Avatar getAvatarById(Long avatarId) {
        return avatarRepository.findById(avatarId)
                .orElseThrow(()-> new AvatarNotFoundException("Avatar not found"));
    }
    private String getExtension(String fileName){
        return null;
    }
}