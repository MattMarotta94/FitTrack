package com.nashss.se.fittrack.lambda;
import com.nashss.se.fittrack.activity.requests.UpdateWorkoutRequest;
import com.nashss.se.fittrack.activity.results.UpdateWorkoutResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * UpdateWorkout Lambda.
 */
public class UpdateWorkoutLambda
        extends LambdaActivityRunner<UpdateWorkoutRequest, UpdateWorkoutResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateWorkoutRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateWorkoutRequest> input, Context context) {
        return super.runActivity(() -> {
                UpdateWorkoutRequest unauthenticatedRequest = input.fromBody(UpdateWorkoutRequest.class);
                return input.fromUserClaims(claims ->
                        UpdateWorkoutRequest.builder()
                                .withEmail(claims.get("email"))
                                .withDate(unauthenticatedRequest.getDate())
                                .withName(unauthenticatedRequest.getName())
                                .withNotes(unauthenticatedRequest.getNotes())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdateWorkoutActivity().handleRequest(request)
    );
    }
}
