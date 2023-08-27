package br.ufpb.dcx.oppfyhub.opportunityhub.repository;

import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Job;
import br.ufpb.dcx.oppfyhub.opportunityhub.enums.TypeJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByTitleJobStartingWith(String titleJob);

    List<Job> findByTypeJob(TypeJob typeJob);

}
