package com.forge.capabilities.requirements;

import com.forge.domain.evidence.EvidenceTopic;
import com.forge.domain.finding.Finding;
import com.forge.domain.requirement.BusinessRequirement;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalRequirementsDiscoveryTest {

    private final LocalRequirementsDiscovery discovery =
            new LocalRequirementsDiscovery();

    @Test
    void shouldCreateUniqueRequirementsFromDistinctEvidenceReferences() {

        EvidenceTopic authenticationTopic =
                new EvidenceTopic(
                        "local-test",
                        "The system must allow users to authenticate.",
                        List.of("evidence-1"));

        EvidenceTopic passwordResetTopic =
                new EvidenceTopic(
                        "local-test",
                        "The system must allow users to reset their password.",
                        List.of("evidence-2"));

        RequirementsDiscoveryOutput output =
                discovery.execute(
                        new RequirementsDiscoveryInput(
                                List.of(
                                        authenticationTopic,
                                        passwordResetTopic)));

        assertEquals(
                2,
                output.businessRequirements().size());

        BusinessRequirement authenticationRequirement =
                output.businessRequirements().get(0);

        BusinessRequirement passwordResetRequirement =
                output.businessRequirements().get(1);

        assertEquals(
                "BR-evidence-1",
                authenticationRequirement.id());

        assertEquals(
                "BR-evidence-2",
                passwordResetRequirement.id());

        assertEquals(
                "The system must allow users to authenticate.",
                authenticationRequirement.description());

        assertEquals(
                "The system must allow users to reset their password.",
                passwordResetRequirement.description());

        assertEquals(
                0,
                output.findings().size());
    }

    @Test
    void shouldCreateAmbiguityFindingForAmbiguousRequirement() {

        EvidenceTopic ambiguousTopic =
                new EvidenceTopic(
                        "local-test",
                        "The system must provide appropriate authentication methods.",
                        List.of("evidence-3"));

        RequirementsDiscoveryOutput output =
                discovery.execute(
                        new RequirementsDiscoveryInput(
                                List.of(ambiguousTopic)));

        assertEquals(
                1,
                output.businessRequirements().size());

        assertEquals(
                1,
                output.findings().size());

        Finding finding =
                output.findings().get(0);

        assertEquals(
                "FINDING-BR-evidence-3",
                finding.id());

        assertEquals(
                "AMBIGUITY",
                finding.type());

        assertEquals(
                "The requirement is ambiguous and requires clarification.",
                finding.description());

        assertEquals(
                List.of("BR-evidence-3"),
                finding.relatedRequirementIds());
    }

    @Test
    void shouldCreateConflictFindingForOppositeRequirements() {

        EvidenceTopic authenticationRequired =
                new EvidenceTopic(
                        "local-test",
                        "The system must allow password authentication.",
                        List.of("evidence-4"));

        EvidenceTopic authenticationForbidden =
                new EvidenceTopic(
                        "local-test",
                        "The system must not allow password authentication.",
                        List.of("evidence-5"));

        RequirementsDiscoveryOutput output =
                discovery.execute(
                        new RequirementsDiscoveryInput(
                                List.of(
                                        authenticationRequired,
                                        authenticationForbidden)));

        assertEquals(
                2,
                output.businessRequirements().size());

        assertEquals(
                1,
                output.findings().size());

        Finding finding =
                output.findings().get(0);

        assertEquals(
                "FINDING-CONFLICT-BR-evidence-4-BR-evidence-5",
                finding.id());

        assertEquals(
                "CONFLICT",
                finding.type());

        assertEquals(
                "The requirements contain conflicting statements.",
                finding.description());

        assertEquals(
                List.of(
                        "BR-evidence-4",
                        "BR-evidence-5"),
                finding.relatedRequirementIds());
    }
}