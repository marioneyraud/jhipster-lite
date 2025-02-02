package tech.jhipster.lite.generator.setup.codespaces.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class CodespacesModuleFactoryTest {

  private static final CodespacesModuleFactory factory = new CodespacesModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture
      .propertiesBuilder(FileUtils.tmpDirForTest())
      .put("serverPort", 8080)
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModule(module).createFiles(".devcontainer/Dockerfile").createFile(".devcontainer/devcontainer.json").containing("8080");
  }
}
