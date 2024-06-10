package com.example.st2i.controller;


import com.example.st2i.service.NotificationService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.st2i.entity.Notification;


import java.util.List;

@RequiredArgsConstructor

@CrossOrigin("*")
@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;


    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> Notifications = notificationService.getAllNotification();
        return new ResponseEntity<>(Notifications, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable("id") Long id) {
        Notification Notification = notificationService.getNotificationById(id);
        return new ResponseEntity<>(Notification, HttpStatus.OK);
    }

    @PostMapping("/add")

    public ResponseEntity<Notification> addNotification(@RequestBody Notification Notification) {

        System.out.println("Notification1"+Notification);

        return new ResponseEntity<>(notificationService.addNotification(Notification),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable("id") Long id, @RequestBody Notification Notification) {


        Notification updatedNotification = notificationService.getNotificationById(id);

        updatedNotification.setContenu(Notification.getContenu());
        updatedNotification.setParent(Notification.getParent());
        updatedNotification.setDate_envoi(Notification.getDate_envoi());


        notificationService.addNotification(updatedNotification);
        return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        notificationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
