package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.model.dto.NotificationDto;
import org.example.model.entity.Notification;
import org.example.model.enums.NotificationChannel;
import org.example.model.enums.NotificationStatus;
import org.example.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/add")
    public NotificationDto createNotification(@RequestBody @Valid NotificationDto request) {
        Notification response = notificationService.createNotification(request);
        return NotificationDto.builder()
                .title(response.getTitle())
                .message(response.getMessage())
                .channel(response.getChannel())
                .status(response.getStatus())
                .createdAt(response.getCreatedAt())
                .sentAt(response.getSentAt())
                .recipientId(response.getRecipient().getId())
                .build();
    }

    @GetMapping("/all")
    public List<NotificationDto> getAllNotifications() {
        return notificationService.getAllNotifications().stream()
                .map(response -> NotificationDto.builder()
                        .title(response.getTitle())
                        .message(response.getMessage())
                        .channel(response.getChannel())
                        .status(response.getStatus())
                        .createdAt(response.getCreatedAt())
                        .sentAt(response.getSentAt())
                        .recipientId(response.getRecipient().getId())
                        .build())
                .toList();
    }

    @GetMapping("/{id}")
    public NotificationDto getNotificationById(@PathVariable Long id) {
        Notification response = notificationService.getNotificationById(id);
        return NotificationDto.builder()
                .title(response.getTitle())
                .message(response.getMessage())
                .channel(response.getChannel())
                .status(response.getStatus())
                .createdAt(response.getCreatedAt())
                .sentAt(response.getSentAt())
                .recipientId(response.getRecipient().getId())
                .build();
    }

    @PutMapping("/{id}")
    public NotificationDto updateNotification(@PathVariable Long id, @RequestBody @Valid NotificationDto request) {
        Notification response = notificationService.updateNotification(id, request);
        return NotificationDto.builder()
                .title(response.getTitle())
                .message(response.getMessage())
                .channel(response.getChannel())
                .status(response.getStatus())
                .createdAt(response.getCreatedAt())
                .sentAt(response.getSentAt())
                .recipientId(response.getRecipient().getId())
                .build();
    }

    @DeleteMapping("/{id}")
    public String deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return "Уведомление удалено";
    }

    @GetMapping("/status/{status}")
    public List<NotificationDto> getByStatus(@PathVariable NotificationStatus status) {
        return notificationService.getNotificationsByStatus(status).stream()
                .map(response -> NotificationDto.builder()
                        .title(response.getTitle())
                        .message(response.getMessage())
                        .channel(response.getChannel())
                        .status(response.getStatus())
                        .createdAt(response.getCreatedAt())
                        .sentAt(response.getSentAt())
                        .recipientId(response.getRecipient().getId())
                        .build())
                .toList();
    }

    @GetMapping("/channel/{channel}")
    public List<NotificationDto> getByChannel(@PathVariable NotificationChannel channel) {
        return notificationService.getNotificationsByChannel(channel).stream()
                .map(response -> NotificationDto.builder()
                        .title(response.getTitle())
                        .message(response.getMessage())
                        .channel(response.getChannel())
                        .status(response.getStatus())
                        .createdAt(response.getCreatedAt())
                        .sentAt(response.getSentAt())
                        .recipientId(response.getRecipient().getId())
                        .build())
                .toList();
    }

    @GetMapping("/recipient/{recipientId}")
    public List<NotificationDto> getByRecipientId(@PathVariable Long recipientId) {
        return notificationService.getNotificationsByRecipientId(recipientId).stream()
                .map(response -> NotificationDto.builder()
                        .title(response.getTitle())
                        .message(response.getMessage())
                        .channel(response.getChannel())
                        .status(response.getStatus())
                        .createdAt(response.getCreatedAt())
                        .sentAt(response.getSentAt())
                        .recipientId(response.getRecipient().getId())
                        .build())
                .toList();
    }
}