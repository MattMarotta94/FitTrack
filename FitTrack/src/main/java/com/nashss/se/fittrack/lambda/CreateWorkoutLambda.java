package com.nashss.se.fittrack.lambda;
import com.nashss.se.fittrack.activity.requests.CreateWorkoutRequest;
import com.nashss.se.fittrack.activity.results.CreateWorkoutResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Creates a workout entry in the workouts table using the CreateWorkoutRequest.
 */
public class CreateWorkoutLambda
        extends LambdaActivityRunner<CreateWorkoutRequest, CreateWorkoutResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateWorkoutRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateWorkoutRequest> input, Context context) {
        return super.runActivity(() -> {
            CreateWorkoutRequest unauthenticatedRequest = input.fromBody(CreateWorkoutRequest.class);
            return input.fromUserClaims(claims ->
                    CreateWorkoutRequest.builder()
                            .withEmail(claims.get("email"))
                            .withName(unauthenticatedRequest.getName())
                            .withDate(unauthenticatedRequest.getDate())
                            .withExercises(unauthenticatedRequest.getExercises())
                            .withNotes(unauthenticatedRequest.getNotes())
                            .build());
            }, (request, serviceComponent) -> serviceComponent.provideCreateWorkoutActivity().handleRequest(request)
        );
    }
}
