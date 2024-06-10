package com.example.st2i.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.st2i.entity.Notification;
import com.example.st2i.repository.NotificationRepository;

import java.util.List;
import java.util.Optional;


@Service
public class NotificationService{
    @Autowired
    private NotificationRepository NotificationRepo;

    public Notification addNotification(Notification Notification) {
        return NotificationRepo.save(Notification);
    }

    public Notification getNotificationById(Long id) {
        Optional<Notification> e= NotificationRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }


    public List<Notification> getAllNotification(){
        return NotificationRepo.findAll();
    }


    public void deleteById(Long id){
        NotificationRepo.deleteById(id);
    }
}
