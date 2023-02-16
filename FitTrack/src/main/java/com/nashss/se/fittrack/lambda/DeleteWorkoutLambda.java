package com.nashss.se.fittrack.lambda;
import com.nashss.se.fittrack.activity.requests.DeleteWorkoutRequest;
import com.nashss.se.fittrack.activity.results.DeleteWorkoutResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * API call to the workouts table.
 * Deletes the requested workout entry.
 */
public class DeleteWorkoutLambda extends LambdaActivityRunner<DeleteWorkoutRequest, DeleteWorkoutResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteWorkoutRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteWorkoutRequest> input, Context context) {
        log.info("DeleteApparatusActivity: handleRequest method accessed.");
        return super.runActivity(() -> {
            DeleteWorkoutRequest unauthenticatedRequest = input.fromUserClaims(claims ->
                    DeleteWorkoutRequest.builder()
                            .withEmail(claims.get("email"))
                            .build());
            log.info("DeleteApparatusActivity: handleRequest method accessed.");
                return input.fromPath(path ->
                        DeleteWorkoutRequest.builder()
                                .withEmail(unauthenticatedRequest.getEmail())
                                .withDate(path.get("date"))
                                .build());
            },
            (request, serviceComponent) ->
                        serviceComponent.provideDeleteWorkoutActivity().handleRequest(request)
        );
    }
}
