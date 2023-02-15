package com.nashss.se.fittrack.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.fittrack.activity.requests.DeleteWorkoutRequest;
import com.nashss.se.fittrack.activity.results.DeleteWorkoutResult;


public class DeleteWorkoutLambda extends LambdaActivityRunner<DeleteWorkoutRequest, DeleteWorkoutResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteWorkoutRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteWorkoutRequest> input, Context context) {
        return super.runActivity(() -> {
                    DeleteWorkoutRequest unauthenticatedRequest = input.fromPath(path ->
                            DeleteWorkoutRequest.builder()
                                    .withDate(path.get("date"))
                                    .build());
                    return input.fromUserClaims(claims ->
                            DeleteWorkoutRequest.builder()
                                    .withEmail(claims.get("email"))
                                    .withDate(unauthenticatedRequest.getDate())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteWorkoutActivity().handleRequest(request)
        );
    }
}
