package com.txvinh.aquariux.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeRequest {
    @NotBlank(message = "Action can not blank")
    @NotEmpty
    private String action;
    @NotBlank(message = "Symbol can not blank")
    @NotEmpty
    private String symbol;
    @Min(0)
    private BigDecimal amount;
    @Min(0)
    private BigDecimal price;
    @Min(0)
    private BigDecimal fee;
}
