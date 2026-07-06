package com.loieexpresses.entity;

/** Generic lifecycle status shared by bookings, quotes, loans, consultations. */
public enum RequestStatus {
    PENDING,
    APPROVED,
    REJECTED,
    IN_PROGRESS,
    COMPLETED
}
