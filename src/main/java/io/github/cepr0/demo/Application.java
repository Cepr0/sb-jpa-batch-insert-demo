package io.github.cepr0.demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@EnableJpaRepositories(considerNestedRepositories = true)
@SpringBootApplication
public class Application {

	private final ModelRepo modelRepo;

	public Application(ModelRepo modelRepo) {
		this.modelRepo = modelRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener
	public void onReady(ApplicationReadyEvent e) {
		modelRepo.saveAll(range(0, 10).mapToObj(Model::new).collect(toList()));
	}

	@Entity
	@NoArgsConstructor
	@Data
	static class Model {
		@Id
		@GeneratedValue
		private UUID id;
	   private Integer number;

		Model(Integer number) {
			this.number = number;
		}

		@PrePersist
		private void prePersist() {
			id = UUID.randomUUID();
		}
	}

	public interface ModelRepo extends JpaRepository<Model, UUID> {
	}
}
