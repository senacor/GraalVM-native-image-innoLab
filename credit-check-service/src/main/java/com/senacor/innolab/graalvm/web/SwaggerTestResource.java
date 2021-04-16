package com.senacor.innolab.graalvm.web;

import com.senacor.innolab.graalvm.web.api.GeneratedResource;
import com.senacor.innolab.graalvm.web.api.models.DummyResult;
import com.senacor.innolab.graalvm.web.api.models.GeneratedGetResponse;
import com.senacor.innolab.graalvm.web.api.models.GeneratedPostRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;

public class SwaggerTestResource implements GeneratedResource {

    @Override
    public Response generatedGet(@NotNull Integer queryValueA) {
        return Response.ok(GeneratedGetResponse.builder()
                .dummyResult(DummyResult.DUMMY1)
                .build())
                .build();
    }

    @Override
    public Response generatedPost(@Valid GeneratedPostRequest body) {
        return Response.ok(GeneratedGetResponse.builder()
                .dummyResult(DummyResult.DUMMY1)
                .build())
                .build();
    }
}
