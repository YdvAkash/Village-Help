package Clarify.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpResponse {

    private boolean success;
    private String message;

    // Optional metadata (future-proof)
    private String email;
    private boolean verified;
}
