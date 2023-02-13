package com.nashss.se.fittrack.lambda;
import com.nashss.se.fittrack.activity.requests.GetExerciseRequest;
import com.nashss.se.fittrack.activity.results.GetExerciseResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetExerciseLambda
        extends LambdaActivityRunner<GetExerciseRequest, GetExerciseResult>
        implements RequestHandler<LambdaRequest<GetExerciseRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetExerciseRequest> input, Context context) {
        return super.runActivity(() -> {
                GetExerciseRequest request = input.fromPath(path ->
                        GetExerciseRequest.builder()
                                .withType(path.get("type"))
                                .build());
                return request;
            },
            (request, serviceComponent) ->
                    serviceComponent.provideGetExerciseActivity().handleRequest(request)
        );
    }
}
