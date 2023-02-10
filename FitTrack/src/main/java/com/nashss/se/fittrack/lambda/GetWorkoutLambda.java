package com.nashss.se.fittrack.lambda;
import com.nashss.se.fittrack.activity.requests.GetWorkoutRequest;
import com.nashss.se.fittrack.activity.results.GetWorkoutResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Makes an API call to the workouts table to retrieve an entry using the users email and the date provided.
 */
public class GetWorkoutLambda
        extends LambdaActivityRunner<GetWorkoutRequest, GetWorkoutResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetWorkoutRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetWorkoutRequest> input, Context context) {
        return super.runActivity(() -> {
            GetWorkoutRequest unauthenticatedRequest = input.fromPath(path ->
                    GetWorkoutRequest.builder()
                            .withDate(path.get("date"))
                            .build());
            return input.fromUserClaims(claims ->
                            GetWorkoutRequest.builder()
                                    .withEmail(claims.get("email"))
                                    .withDate(unauthenticatedRequest.getDate())
                                    .build());
            },
            (request, serviceComponent) ->
                serviceComponent.provideGetWorkoutActivity().handleRequest(request)
                );
    }
}
