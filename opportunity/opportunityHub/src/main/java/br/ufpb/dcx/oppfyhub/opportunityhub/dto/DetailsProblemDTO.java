package br.ufpb.dcx.lab1v1.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailsProblemDTO {
    private int status;
    private String type;
    private String title;
    private String detail;
}