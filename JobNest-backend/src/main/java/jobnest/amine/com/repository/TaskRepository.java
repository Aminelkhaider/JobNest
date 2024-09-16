package jobnest.amine.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jobnest.amine.com.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
  List<Task> findAllByUserId(Long id);
}
