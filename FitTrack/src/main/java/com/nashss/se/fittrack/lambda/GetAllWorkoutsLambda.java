package com.nashss.se.fittrack.lambda;

import com.nashss.se.fittrack.activity.requests.GetAllWorkoutsRequest;
import com.nashss.se.fittrack.activity.results.GetAllWorkoutsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * API call to the workouts table.
 * Uses the provided users email and returns all workouts corresponding to that email.
 */
public class GetAllWorkoutsLambda  extends LambdaActivityRunner<GetAllWorkoutsRequest, GetAllWorkoutsResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllWorkoutsRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllWorkoutsRequest> input, Context context) {
        return super.runActivity(() -> {
                return input.fromUserClaims(claims ->
                        GetAllWorkoutsRequest.builder()
                                .withEmail(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideGetAllWorkoutsActivity().handleRequest(request)
    );
    }
}
