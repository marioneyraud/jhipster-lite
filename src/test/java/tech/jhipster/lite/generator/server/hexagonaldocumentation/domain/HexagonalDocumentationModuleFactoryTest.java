package tech.jhipster.lite.generator.server.hexagonaldocumentation.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class HexagonalDocumentationModuleFactoryTest {

  private static final HexagonalDocumentationModuleFactory factory = new HexagonalDocumentationModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(FileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, readmeFile())
      .createPrefixedFiles("documentation", "hexagonal-architecture.md", "hexagonal-flow.png", "hexagonal-global-schema.png")
      .createFile("README.md")
      .containing("[Hexagonal architecture](documentation/hexagonal-architecture.md)");
  }
}
