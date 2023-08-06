package br.ufpb.dcx.lab1v1.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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