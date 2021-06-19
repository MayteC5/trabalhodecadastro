package br.com.cadastrodedisciplina;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.com.cadastrodedisciplina");

        noClasses()
            .that()
            .resideInAnyPackage("br.com.cadastrodedisciplina.service..")
            .or()
            .resideInAnyPackage("br.com.cadastrodedisciplina.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..br.com.cadastrodedisciplina.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
