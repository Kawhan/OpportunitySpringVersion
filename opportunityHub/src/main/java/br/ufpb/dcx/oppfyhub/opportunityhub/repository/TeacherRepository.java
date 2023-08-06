package br.ufpb.dcx.oppfyhub.opportunityhub.repository;

import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByName(String name);

    Optional<Teacher> findById(long id);
}
