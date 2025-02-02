package tech.jhipster.lite.generator.client.angular.security.jwt.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.angular.common.domain.AngularCommonService;
import tech.jhipster.lite.generator.client.angular.security.jwt.domain.AngularJwtDomainService;
import tech.jhipster.lite.generator.client.angular.security.jwt.domain.AngularJwtService;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@Configuration
public class AngularJwtBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final NpmService npmService;
  private final AngularCommonService angularCommonService;

  public AngularJwtBeanConfiguration(
    ProjectRepository projectRepository,
    NpmService npmService,
    AngularCommonService angularCommonService
  ) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
    this.angularCommonService = angularCommonService;
  }

  @Bean
  public AngularJwtService angularJwtService() {
    return new AngularJwtDomainService(projectRepository, npmService, angularCommonService);
  }
}
