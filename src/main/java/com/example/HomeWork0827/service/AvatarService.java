package com.example.HomeWork0827.service;

import com.example.HomeWork0827.model.Avatar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarService {
    Avatar uploadAvatar(Long studentId, MultipartFile file) throws IOException;
    Avatar getAvatarById(Long avatarId);
}
