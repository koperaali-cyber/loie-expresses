package com.loieexpresses.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Handles uncaught application exceptions and shows a friendly error page.
 *
 * Note: Spring Security exceptions (authentication / access-denied) are intentionally
 * NOT handled here so that the security filter chain can perform its normal redirect
 * to the login page. We only catch generic runtime errors from controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntime(RuntimeException ex, Model model) {
        model.addAttribute("code", 500);
        model.addAttribute("message",
                ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred.");
        return "error";
    }
}
