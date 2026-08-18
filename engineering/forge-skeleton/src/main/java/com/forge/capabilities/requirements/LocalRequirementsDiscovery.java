package com.forge.capabilities.requirements;

import com.forge.domain.evidence.EvidenceTopic;
import com.forge.domain.finding.Finding;
import com.forge.domain.requirement.BusinessRequirement;

import java.util.ArrayList;
import java.util.List;

public final class LocalRequirementsDiscovery
        implements RequirementsDiscoveryCapability {

    @Override
    public RequirementsDiscoveryOutput execute(
            RequirementsDiscoveryInput input) {

        List<BusinessRequirement> requirements =
                input.evidenceTopics()
                        .stream()
                        .map(this::toRequirement)
                        .toList();

        List<Finding> findings =
                new ArrayList<>();

        for (BusinessRequirement requirement : requirements) {
            if (isAmbiguous(requirement)) {
                findings.add(
                        new Finding(
                                "FINDING-" + requirement.id(),
                                "AMBIGUITY",
                                "The requirement is ambiguous and requires clarification.",
                                List.of(requirement.id())));
            }
        }

        findings.addAll(
                detectConflicts(requirements));

        findings.addAll(
                detectUnresolvedDependencies(requirements));

        findings.addAll(
                detectCircularDependencies(requirements));

        return new RequirementsDiscoveryOutput(
                requirements,
                findings);
    }

    private BusinessRequirement toRequirement(
            EvidenceTopic topic) {

        String requirementId =
                "BR-" + topic.evidenceReferences().get(0);

        return new BusinessRequirement(
                requirementId,
                topic.name(),
                topic.information(),
                "LOCAL",
                List.of());
    }

    private boolean isAmbiguous(
            BusinessRequirement requirement) {

        String description =
                requirement.description().toLowerCase();

        return description.contains("some")
                || description.contains("appropriate")
                || description.contains("suitable")
                || description.contains("as needed")
                || description.contains("etc.");
    }

    private List<Finding> detectConflicts(
            List<BusinessRequirement> requirements) {

        List<Finding> findings =
                new ArrayList<>();

        for (int i = 0; i < requirements.size(); i++) {
            BusinessRequirement first =
                    requirements.get(i);

            for (int j = i + 1; j < requirements.size(); j++) {
                BusinessRequirement second =
                        requirements.get(j);

                if (isConflict(first, second)) {
                    findings.add(
                            new Finding(
                                    "FINDING-CONFLICT-"
                                            + first.id()
                                            + "-"
                                            + second.id(),
                                    "CONFLICT",
                                    "The requirements contain conflicting statements.",
                                    List.of(
                                            first.id(),
                                            second.id())));
                }
            }
        }

        return findings;
    }

    private boolean isConflict(
            BusinessRequirement first,
            BusinessRequirement second) {

        String firstDescription =
                first.description().toLowerCase();

        String secondDescription =
                second.description().toLowerCase();

        return hasSameSubjectWithOppositeConstraint(
                firstDescription,
                secondDescription);
    }

    private boolean hasSameSubjectWithOppositeConstraint(
            String first,
            String second) {

        if (first.contains("must ")
                && second.contains("must not ")) {

            return normalizeSubject(
                    first.replace("must not ", "must "))
                    .equals(
                            normalizeSubject(second));
        }

        if (first.contains("must not ")
                && second.contains("must ")) {

            return normalizeSubject(first)
                    .equals(
                            normalizeSubject(
                                    second.replace(
                                            "must not ",
                                            "must ")));
        }

        return false;
    }

    private List<Finding> detectUnresolvedDependencies(
            List<BusinessRequirement> requirements) {

        List<Finding> findings =
                new ArrayList<>();

        for (BusinessRequirement requirement : requirements) {
            String description =
                    requirement.description().toLowerCase();

            if (hasUnresolvedDependency(
                    description,
                    requirements,
                    requirement)) {

                findings.add(
                        new Finding(
                                "FINDING-DEPENDENCY-"
                                        + requirement.id(),
                                "UNRESOLVED_DEPENDENCY",
                                "The requirement depends on another capability or condition that has not been defined.",
                                List.of(requirement.id())));
            }
        }

        return findings;
    }

    private boolean hasUnresolvedDependency(
            String description,
            List<BusinessRequirement> requirements,
            BusinessRequirement currentRequirement) {

        if (description.contains("not defined")
                || description.contains("undefined")
                || description.contains("unspecified")) {
            return true;
        }

        String dependencyTarget =
                extractDependencyTarget(description);

        if (dependencyTarget == null) {
            return false;
        }

        return !isDependencyDefined(
                dependencyTarget,
                requirements,
                currentRequirement);
    }

    private boolean isDependencyDefined(
            String dependencyTarget,
            List<BusinessRequirement> requirements,
            BusinessRequirement currentRequirement) {

        for (BusinessRequirement requirement : requirements) {
            if (requirement.id().equals(currentRequirement.id())) {
                continue;
            }

            if (refersTo(
                    dependencyTarget,
                    requirement.description().toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    private List<Finding> detectCircularDependencies(
            List<BusinessRequirement> requirements) {

        List<Finding> findings =
                new ArrayList<>();

        for (int i = 0; i < requirements.size(); i++) {
            BusinessRequirement first =
                    requirements.get(i);

            for (int j = i + 1; j < requirements.size(); j++) {
                BusinessRequirement second =
                        requirements.get(j);

                if (isCircularDependency(first, second)) {
                    findings.add(
                            new Finding(
                                    "FINDING-CIRCULAR-"
                                            + first.id()
                                            + "-"
                                            + second.id(),
                                    "CIRCULAR_DEPENDENCY",
                                    "The requirements contain a circular dependency.",
                                    List.of(
                                            first.id(),
                                            second.id())));
                }
            }
        }

        return findings;
    }

    private boolean isCircularDependency(
            BusinessRequirement first,
            BusinessRequirement second) {

        String firstDescription =
                first.description().toLowerCase();

        String secondDescription =
                second.description().toLowerCase();

        String firstDependency =
                extractDependencyTarget(firstDescription);

        String secondDependency =
                extractDependencyTarget(secondDescription);

        return firstDependency != null
                && secondDependency != null
                && refersTo(
                firstDependency,
                secondDescription)
                && refersTo(
                secondDependency,
                firstDescription);
    }

    private String extractDependencyTarget(
            String description) {

        int dependsOnIndex =
                description.indexOf("depends on");

        if (dependsOnIndex >= 0) {
            return description.substring(
                            dependsOnIndex + "depends on".length())
                    .trim()
                    .replace(".", "");
        }

        int dependentOnIndex =
                description.indexOf("dependent on");

        if (dependentOnIndex >= 0) {
            return description.substring(
                            dependentOnIndex + "dependent on".length())
                    .trim()
                    .replace(".", "");
        }

        return null;
    }

    private boolean refersTo(
            String dependency,
            String description) {

        String normalizedDependency =
                dependency
                        .replaceAll("[^a-z0-9 ]", " ")
                        .replaceAll("\\s+", " ")
                        .trim();

        String normalizedDescription =
                description
                        .replaceAll("[^a-z0-9 ]", " ")
                        .replaceAll("\\s+", " ")
                        .trim();

        return normalizedDescription.contains(
                normalizedDependency);
    }

    private String normalizeSubject(
            String description) {

        return description
                .replace("must not ", "must ")
                .replaceAll("\\s+", " ")
                .trim();
    }
}