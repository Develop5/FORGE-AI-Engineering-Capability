package com.forge.orchestration;

import com.forge.domain.execution.Execution;

public interface ForgeEngine {

    Execution start(Execution execution);
}