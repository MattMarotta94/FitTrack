package com.nashss.se.fittrack.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.fittrack.activity.requests.CreateWorkoutRequest;
import com.nashss.se.fittrack.activity.results.CreateWorkoutResult;

public class CreateWorkoutLambda
        extends LambdaActivityRunner<CreateWorkoutRequest, CreateWorkoutResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateWorkoutRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateWorkoutRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateWorkoutRequest unauthenticatedRequest = input.fromBody(CreateWorkoutRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateWorkoutRequest.builder()
                                    .withName(unauthenticatedRequest.getName())
                                    .withDate(unauthenticatedRequest.getDate())
                                    .withNotes(unauthenticatedRequest.getNotes())
                                    .withExerciseList(unauthenticatedRequest.getExerciseList())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateWorkoutActivity().handleRequest(request)
        );
    }
}