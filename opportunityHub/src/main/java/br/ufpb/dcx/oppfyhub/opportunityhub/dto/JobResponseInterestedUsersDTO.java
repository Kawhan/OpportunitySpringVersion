package br.ufpb.dcx.oppfyhub.opportunityhub.dto;

import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Job;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class JobResponseInterestedUsersDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany()
    List<User> interestedUsers;

    public static JobResponseInterestedUsersDTO from(Job job) {
        return JobResponseInterestedUsersDTO
                .builder()
                .id(job.getId())
                .interestedUsers(job.getInterestedUsers())
                .build();
    }
}
