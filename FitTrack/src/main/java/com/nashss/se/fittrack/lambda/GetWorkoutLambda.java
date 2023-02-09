package com.nashss.se.fittrack.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.fittrack.activity.requests.CreateWorkoutRequest;
import com.nashss.se.fittrack.activity.requests.GetWorkoutRequest;
import com.nashss.se.fittrack.activity.results.GetWorkoutResult;

public class GetWorkoutLambda
        extends LambdaActivityRunner<GetWorkoutRequest, GetWorkoutResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetWorkoutRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetWorkoutRequest> input, Context context) {
        return super.runActivity( () -> {
            GetWorkoutRequest unauthenticatedRequest = input.fromBody(GetWorkoutRequest.class);
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
